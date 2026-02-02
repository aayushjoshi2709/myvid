#### encoding service installation
1. Install Java 21+
2. Navigate to the **encodingservice (/server/encodingservice)** directory.
3. Install the **ffmpeg** package, which is used to encode videos and images.

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
4. Install the required dependencies using Maven 
```
   ./mvnw clean install
```

5. Create an **application-local.yml**  file and add configuration similar to the example shown below.
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
6. now you can build it using mvnw build
```
    ./mvnw package
```
7. run it
```
    ./mvnw spring-boot:run
```
