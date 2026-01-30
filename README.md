# Vitis API

First version of a CRUD API about wineries and wine, made with Spring Boot.

## Setup

### Requires

- Java 21
- Docker

### Database deployment

```bash
docker compose up -d
docker exec -it postgres-spring-boot bash
psql -U user
CREATE DATABASE vitis_api;
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