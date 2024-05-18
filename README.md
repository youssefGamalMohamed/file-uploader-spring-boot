# File Uploader Spring Boot

This is a simple file uploader application built with Spring Boot. It allows users to upload files to the server and store them in a MySQL database.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)

## Installation

To get started with this application, you'll need to clone the repository:

```bash
git clone https://github.com/youssefGamalMohamed/file-uploader-spring-boot.git
```

Then, navigate to the project directory:

```bash
cd file-uploader-spring-boot
```

## Configuration

Before running the application, make sure to configure the `application.yml` file located in the `src/main/resources` directory. Here's an example configuration:

```yaml
server:
  port: 9090
  servlet:
    context-path: /file-storage/api/v1

spring:
  application:
    name: file-uploader-spring-boot

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/file_storage
    username: root
    password: 1234
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 20MB
```

Ensure that the MySQL database connection details (`url`, `username`, and `password`) are correctly set according to your environment. Also, adjust the `max-file-size` and `max-request-size` properties to meet your requirements.

## Usage

Once the configuration is done, you can run the application using your preferred IDE or by executing the following Maven command:

```bash
mvn spring-boot:run
```

The application will start, and you can access it through your web browser or any HTTP client. The base URL for the API endpoints is `http://localhost:9090/file-storage/api/v1`.

## Endpoints

1. **Upload Files Endpoint**:
   - **Method**: POST
   - **URL**: `/files`
   - **Description**: Upload one or more files to the server.
   - **Request Parameters**:
     - `files`: List of files to be uploaded.
   - **Response**: Returns a list of uploaded files with their corresponding URLs.

2. **Find File by ID Endpoint**:
   - **Method**: GET
   - **URL**: `/files/{id}`
   - **Description**: Retrieve a file by its ID.
   - **Path Parameters**:
     - `id`: ID of the file to retrieve.
   - **Response**: Returns the file with the specified ID.

## Contributing

Contributions are welcome! If you have any ideas, improvements, or bug fixes, feel free to open an issue or create a pull request.
