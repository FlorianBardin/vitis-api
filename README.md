# Vitis API

First version of a CRUD API about wineries and wine, made with Spring Boot.

## Installation & Usage

### Requirement

- Java 21
- Docker

### Installation

```
# Clone the repository
git clone https://github.com/FlorianBardin/vitis-api

# Navigate into the project
cd  vitis-api
```

### Database deployment

```bash
# Create containers
docker compose up -d

# Launch database bash
docker exec -it postgres-spring-boot bash

# Connect as user
psql -U user

# Create the database
CREATE DATABASE vitis_api;
```

### Start the server
```
./mvnw spring-boot:run
```

## Features

### Functional

- **RESTful CRUD Operations**: Full management of wines and wineries resources.
- **Search Engine**: Dynamic filtering system for wines (using JPA Specifications).
- **Data Integrity**: Strict input validation rules preventing corrupted data storage.

### Technical

- **Robust Error Handling**: Centralized exception handling implementing the IETF RFC 7807 standard (Problem Details) for explicit and standardized API errors.
- **Secure Architecture**: Hiding internal database structure using Data Transfer Objects.
- **Layered Architecture**: Clean structure (Controller -> Service -> Repository).
- **Developer Experience (DX)**: Database seeding with mock data (Datafaker) to improve development experience and efficiency.

### Coming soon

- Documentation
- Unit tests and integration tests
- Pagination
- Authentication
- Monitoring
- Full containerization

## Project structure

**Winery** table :
```sql
- id integer not null
- address varchar(255)
- name varchar(255) not null
- region varchar(255) not null
```

**Wine** table :
```sql
- id integer not null,
- price float(53),
- stock integer,
- vintage integer not null,
- winery_id integer,
- color varchar(255) not null,
- name varchar(255) not null,
```