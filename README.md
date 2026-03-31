# Application Properties

- Swagger Link : http://localhost:8080/swagger-ui/index.html#/

# Doc Links

- MySQL Integration : https://spring.io/guides/gs/accessing-data-mysql/
- SpringDoc Integration : https://springdoc.org/
- Mongodb
  Integration : https://medium.com/@samuelcatalano/connecting-spring-boot-to-mongodb-a-step-by-step-guide-b9f2fd9e872d
- MongoDb Installation : https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/
- Redis Cache : https://medium.com/simform-engineering/spring-boot-caching-with-redis-1a36f719309f
- Kafka
  Integration : https://medium.com/@abhishekranjandev/a-comprehensive-guide-to-integrating-kafka-in-a-spring-boot-application-a4b912aee62e
-

Models/Entities : https://www.baeldung.com/java-entity-vs-dto#:~:text=In%20our%20example%2C%20the%20entities,the%20Model%20or%20Domain%20Model.

- Error handling in Spring
  Boot : https://medium.com/@aedemirsen/spring-boot-global-exception-handler-842d7143cf2a
- Java8 Time Library : https://www.baeldung.com/java-8-date-time-intro

# Prerequisites

- Docker : `brew install --cask docker`

# Services Start/Restart/Stop

### Start all services (MySQL, MongoDB, Redis, Kafka)

```bash
docker compose up -d
```

### Stop all services

```bash
docker compose down
```

### Stop and remove volumes (wipes data)

```bash
docker compose down -v
```

### Individual services

```bash
# Start only specific services
docker compose up -d mysql mongodb redis

# View logs
docker compose logs -f mysql

# Restart a single service
docker compose restart mysql
```

### MySQL
- Runs on `localhost:3306`
- Root password: `root123` | Database: `curses`
- Connect via CLI: `docker exec -it mysql mysql -uroot -proot123`
- UI client: [Sequel Ace](https://sequel-ace.com/) (`brew install --cask sequel-ace`)

### MongoDB
- Runs on `localhost:27017`
- Connect via CLI: `docker exec -it mongodb mongosh`

### Redis
- Runs on `localhost:6379`
- Check if running: `docker exec -it redis redis-cli ping`

### Kafka (KRaft mode, no Zookeeper)
- Runs on `localhost:9092`
- Uses KRaft (built-in consensus) — no Zookeeper dependency

# Notes

- **Modules Introduction** : During multi module introduction, keep track of basePackages in @EnableRepositories, @ComponentScan and similar annotations.
- **Hibernate** : Hibernate is java based ORM tool that provides framework for mapping application domain objects to
  the relational database tables and vice versa. Separate Dependency injection not required as
  Spring Data includes hibernate dependency.
- **Swagger** : Spring Boot 3.x requires to use version 2 of springdoc-openapi and springdoc-openapi dependency
  already includes Swagger UI.
- **CRUD** : CRUD refers Create, Read, Update, Delete operations.
- **Spring Beans** : By default, beans are singleton in Spring. Singleton beans are created only once and shared in
  application.
