# MyVid

**MyVid** is a video-sharing platform similar to YouTube or Twitch, designed to provide a rich ecosystem for uploading, sharing, and viewing videos.

## Services

The platform currently consists of three core services:

### 1. IO Service
Handles all core platform data, including videos, users, and comments. This service acts as the primary backend responsible for data storage and retrieval.

### 2. Encoding Service
Processes uploaded media files by encoding videos and images into multiple resolutions and chunks. This enables efficient streaming and adaptive bitrate playback.

### 3. Client
The frontend application of the platform. It allows users to upload content, browse videos, watch streams, and interact through comments.


## How to setup

### Manual Setup

#### IO Service

1. Navigate to the **ioservice** directory.
2. Install the required dependencies using Maven wrapper
```
   ./mvnw clean install
```

3. Create an **application-local.yml**  file and add configuration similar to the example shown below.

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

4. now you can build it using mvnw build
```
    ./mvnw package
```
5. run it
```
    ./mvnw spring-boot:run
```

#### encoding service
1. Navigate to the **encodingservice** directory.
2. Install the **ffmpeg** package, which is used to encode videos and images.

**Debian / Ubuntu**
```
   sudo apt update
   sudo apt install ffmpeg
```
**Fedora**
```
    sudo dnf install ffmpeg
```
**macOS**
```
    brew install ffmpeg
```
3. Install the required dependencies using Maven 
```
   ./mvnw clean install
```

4. Create an **application-local.yml**  file and add configuration similar to the example shown below.
```
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
    temp-store:
        video:
        src: "./temp/src/video/"
        dest: "./temp/dest/video/"
        image:
        src: "./temp/src/image/"
        dest: "./temp/dest/image/"
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