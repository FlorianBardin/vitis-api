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
### Project structure

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