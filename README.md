# Java Spring Boot

2 Services: Customer and Fraud
I use the microservices architecture to decouple both services with their own controller, service, repository, and model class. Due to resource limitations, they share a PostgreSQL DB instance. 

The Fraud service is not built out completely and there is a rough implementation of adding new customers to the fraud-check list. This can be built out completely in the future.

The project uses Java, SpringBoot, RestTemplate for communication, PostgreSQL, PgAdmin, and Docker for functionality on the localhost.

Additional tasks for exploration will include zipkin/Spring Cloud sleuth, and Eureka server, and Kubernetes implementation.
