"""
Locust load test for Gojo-And-SpringBoot service APIs.

Usage:
  locust -f locustfile.py --host http://localhost:8081

Optional:
  LOCUST_INCLUDE_KAFKA=true locust -f locustfile.py --host http://localhost:8081
"""

import json
import os
import random
import uuid
from collections import deque

from locust import HttpUser, between, task


class GojoServiceUser(HttpUser):
    wait_time = between(1, 3)
    host = os.getenv("TARGET_HOST", "http://localhost:8081")

    def on_start(self):
        self.user_ids = deque(maxlen=200)
        self.student_ids = deque(maxlen=200)
        self.book_last_names = deque(maxlen=200)
        self.include_kafka = os.getenv("LOCUST_INCLUDE_KAFKA", "false").lower() in {
            "1",
            "true",
            "yes",
            "y",
        }

    @staticmethod
    def _is_success(status_code: int) -> bool:
        return 200 <= status_code < 300

    @staticmethod
    def _pick(items):
        return random.choice(list(items)) if items else None

    def _create_user(self):
        suffix = uuid.uuid4().hex[:8]
        payload = {
            "userId": random.randint(10_000, 999_999),
            "userName": f"locust-user-{suffix}",
            "userEmail": f"locust-{suffix}@example.com",
        }
        with self.client.post(
            "/db/user/add",
            json=payload,
            name="POST /db/user/add",
            catch_response=True,
        ) as response:
            if not self._is_success(response.status_code):
                response.failure(f"Unexpected status: {response.status_code}")
                return None
            try:
                body = response.json()
            except ValueError:
                response.failure("Response is not valid JSON")
                return None

            entity_id = body.get("id")
            if entity_id is None:
                response.failure("User ID missing in response")
                return None

            self.user_ids.append(entity_id)
            response.success()
            return entity_id

    def _create_student(self):
        suffix = uuid.uuid4().hex[:8]
        payload = {
            "sid": random.randint(10_000, 999_999),
            "name": f"locust-student-{suffix}",
            "email": f"locust-student-{suffix}@example.com",
            "contact": f"+91{random.randint(1000000000, 9999999999)}",
        }
        with self.client.post(
            "/cache/save",
            json=payload,
            name="POST /cache/save",
            catch_response=True,
        ) as response:
            if not self._is_success(response.status_code):
                response.failure(f"Unexpected status: {response.status_code}")
                return None

            try:
                body = json.loads(response.text)
            except json.JSONDecodeError:
                response.failure("Response is not valid JSON text")
                return None

            entity_id = body.get("id")
            if entity_id is None:
                response.failure("Student ID missing in response")
                return None

            self.student_ids.append(entity_id)
            response.success()
            return entity_id

    @task(5)
    def housekeeping_apis(self):
        prefix = f"LC-{uuid.uuid4().hex[:6]}"
        self.client.get("/starting/hello", name="GET /starting/hello")
        self.client.get(
            f"/starting/getUniqueWorkflowId?prefix={prefix}",
            name="GET /starting/getUniqueWorkflowId",
        )
        self.client.get("/logging", name="GET /logging")

    @task(4)
    def user_db_apis(self):
        entity_id = self._create_user()
        if entity_id is None:
            return

        with self.client.get(
            f"/db/user/get-by-id/{entity_id}",
            name="GET /db/user/get-by-id/{id}",
            catch_response=True,
        ) as response:
            if not self._is_success(response.status_code):
                response.failure(f"Unexpected status: {response.status_code}")
            else:
                response.success()

        self.client.get("/db/user/get-all-users", name="GET /db/user/get-all-users")

    @task(3)
    def book_db_apis(self):
        suffix = uuid.uuid4().hex[:6]
        last_name = f"ln-{suffix}"
        payload = {
            "sid": f"book-{suffix}",
            "firstName": "Load",
            "lastName": last_name,
        }
        with self.client.post(
            "/db/book/add",
            json=payload,
            name="POST /db/book/add",
            catch_response=True,
        ) as response:
            if not self._is_success(response.status_code):
                response.failure(f"Unexpected status: {response.status_code}")
                return
            self.book_last_names.append(last_name)
            response.success()

        query_last_name = self._pick(self.book_last_names) or last_name
        # Endpoint mapping uses both a path segment and query param.
        self.client.get(
            f"/db/book/{query_last_name}?lastname={query_last_name}",
            name="GET /db/book/{lastname}",
        )

    @task(4)
    def student_cache_apis(self):
        entity_id = self._create_student()
        if entity_id is None:
            return

        with self.client.get(
            f"/cache/find-by-id/{entity_id}?id={entity_id}",
            name="GET /cache/find-by-id/{id}",
            catch_response=True,
        ) as response:
            if not self._is_success(response.status_code):
                response.failure(f"Unexpected status: {response.status_code}")
            else:
                response.success()

    @task(2)
    def state_machine_apis(self):
        self.client.get(
            "/state-machine/start-machine",
            name="GET /state-machine/start-machine",
        )
        for event in ("E1", "E2", "FINAL_EVENT"):
            self.client.post(
                f"/state-machine/send-event?event={event}",
                name="POST /state-machine/send-event",
            )

    @task(1)
    def kafka_apis(self):
        if not self.include_kafka:
            return

        message = f"locust-message-{uuid.uuid4().hex[:8]}"
        self.client.post(
            f"/kafka/publish-message?topic=load-test-topic&message={message}",
            name="POST /kafka/publish-message",
        )
        self.client.post(
            "/kafka/publish-student",
            json={
                "sid": f"kafka-{uuid.uuid4().hex[:6]}",
                "name": "Kafka Locust",
                "email": f"kafka-{uuid.uuid4().hex[:6]}@example.com",
                "contact": f"+91{random.randint(1000000000, 9999999999)}",
            },
            name="POST /kafka/publish-student",
        )
