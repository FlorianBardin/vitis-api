# Vitis API
## Florian Bardin

## Setup

### Requires

- Docker

### Database

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