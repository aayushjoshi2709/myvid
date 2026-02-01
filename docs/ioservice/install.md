
#### IO Service Installation
1. Install Java 21+
2. Navigate to the **ioservice** directory.
3. Install the required dependencies using Maven wrapper
```
   ./mvnw clean install
```

4. Create an **application-local.yml**  file and add configuration similar to the example shown below.

```
    jwt:
    secret: kj3ls32#9043un4OY$m3sare4r
    spring:
    datasource:
        url: jdbc:postgresql://localhost:5432/myvid
        username: postgres
        password: postgres
        driver-class-name: org.postgresql.Driver
    jpa:
        database-platform: org.hibernate.dialect.PostgreSQLDialect
        hibernate:
        ddl-auto: update
        show-sql: true
        properties:
        hibernate:
            format_sql: true
    aws:
    sqs:
        data-processing:
        queue-url: http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-vid-data-processing-queue
        processed-data:
        queue-url: http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-vid-processed-data-queue
    s3:
        data-processing:
        bucket-name: my-vid-data-processing-bucket
        processed-data:
        bucket-name: my-vid-data-processed-bucket
    region: us-east-1
    access-key: test
    secret-key: test
    pathAccess: true
    endpoint: http://localhost:4566
```

5. now you can build it using mvnw build
```
    ./mvnw package
```
6. run it
```
    ./mvnw spring-boot:run
```

