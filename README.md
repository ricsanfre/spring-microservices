# Microservices

- Microservices Discovery with Eureka Server.
- Microservices REST clients with OpenFeign
- Distributed tracing with Springboot actuator and micrometer
    - Exporting traces to zipkin server
- API gateway with Spring Cloud
- Message queue with Rabbit MQ and Kafka

## Message brokers

### Rabbit MQ

See Spring-boot documentation about how to use AMPQ protocol and Rabbit MQ support: (https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging.amqp)

### Apache Kafka 

See Spring-boot documentation about Kafka: (https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging.kafka)

## Distributed tracing

See details in spring boot documentation: [Spring-boot Distributing tracing](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.micrometer-tracing)

- Install Maven dependencies
  
  Actuator, Micrometer's tracer bridge to OTEL and Zipkin exporter

  ```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-tracing-bridge-otel</artifactId>
    </dependency>
    
    <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-zipkin</artifactId>
    </dependency> 
  </dependencies> 
  ```
  
  Also, if OpenFeign is used as rest Client micrometer dependency need to be added:

  ```xml
  <dependency>
     <groupId>io.github.openfeign</groupId>
     <artifactId>feign-micrometer</artifactId>
  </dependency>
  ```

- Configure application:

  ```yaml
  management:
    # No sampling configuration
    tracing:
      sampling:
        probability: 1.0
    # export traces to a zipkin server
    zipkin:
      tracing:
        endpoint: http://localhost:9411/api/v2/spans
  # Configure logging to add traceId and SpanId
  logging:
    pattern:
      level: "%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]"
  ```
  
### Enable context propagation through Message bus

TraceId can be propagated through Kafka
See Spring-kafka Micrometer integration documentation: https://docs.spring.io/spring-kafka/reference/kafka/micrometer.html

and Spring-AMQP Micrometer integration documentation: https://docs.spring.io/spring-amqp/docs/current/reference/html/#micrometer-observation 


## Build

Maven multi-module project:

- Initial scaffolding: Generate basic Maven parent project

  ```shell
  mvn archetype:generate -DgroupId=com.ricsanfre.microservices -DartifactId=microservices -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
  ```


- Generate microservices docker images and upload to Docker hub

  ```shell
  mvn clean package -P build-docker-image
  ```

## Kubernetes

Create a local cluster using [K3D](https://k3d.io/)

- Start k3d cluster

  ```shell
  k3d cluster create -c k3d-cluster.yml
  ```
  
- Deploy basic services:

  

## TODO

[] Check Spring-cloud kubernetes doc https://docs.spring.io/spring-cloud-kubernetes/docs/current/reference/html/
[] Configure security between microservices

## References

- [Microservices with Spring Cloud](https://spring.io/microservices)
- [Spring Cloud](https://spring.io/cloud)
- [Microservices Discovery Service: Eureka ](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
- [Sprig Cloud Open Feign](https://spring.io/projects/spring-cloud-openfeign)
- [Spring-boot app banner generator](https://devops.datenkollektiv.de/banner.txt/index.html)
- [Spring-boot Distributing tracing](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.micrometer-tracing)
- [Zipkin](https://zipkin.io/)
- [Spring Cloud gateway](https://spring.io/projects/spring-cloud-gateway)
- [Configuring RabbitMQ with SpringBoot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging.amqp)