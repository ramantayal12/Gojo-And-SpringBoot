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

# Services Start/Restart/Stop

- Install Mysql 
  - `brew services start mysql`
  - `brew install sequel-ace` : UI for mysql dB.

- Install Redis
  - `brew install redis`
  - `brew services start redis`

- Check for redis-working
  - `redis-cli ping`
  - `brew install redis-pro` : similar to sequel pro for redis

- Install Kafka
  - `brew install kafka`
  - `brew services start kafka`

- Install Zookeeper
  - `brew install zookeeper`
  - `zkserver start`

- Install MongoDB
  - `brew install mongodb-community`
  - `brew services start mongodb/brew/mongodb-community`

- Install Docker
  - `brew install --cask docker`

# Notes

- **Hibernate** : Hibernate is java based ORM tool that provides framework for mapping application domain objects to
  the relational database tables and vice versa. Separate Dependency injection not required as
  Spring Data includes hibernate dependency.
- **Swagger** : Spring Boot 3.x requires to use version 2 of springdoc-openapi and springdoc-openapi dependency
  already includes Swagger UI.
- **CRUD** : CRUD refers Create, Read, Update, Delete operations.
- **Spring Beans** : By default, beans are singleton in Spring. Singleton beans are created only once and shared in
  application.