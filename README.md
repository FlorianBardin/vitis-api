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

### Endpoints

**Base URL:** `http://localhost:8080`

| Method     | Path             | Description                                                                           |
|:-----------|:-----------------|:--------------------------------------------------------------------------------------|
| **GET**    | `/wineries`      | Get all wineries                                                                      |
| **GET**    | `/wineries/{id}` | Get a winery by ID                                                                    |
| **POST**   | `/wineries`      | Create a new winery                                                                   |
| **PUT**    | `/wineries/{id}` | Update a winery                                                                       |
| **DELETE** | `/wineries/{id}` | Delete a winery                                                                       |
| ---        | ---              | ---                                                                                   |
| **GET**    | `/wines`         | Get all wines (paginated by default)                                                  |
| **GET**    | `/wines/{id}`    | Get a wine by ID                                                                      |
| **GET**    | `/wines`         | **Search & Filter**<br/>Params: `name`, `color`, `vintage`, `minPrice`, `maxPrice`, `type` |
| **POST**   | `/wines`         | Create a new wine                                                                     |
| **PUT**    | `/wines/{id}`    | Update a wine                                                                         |
| **DELETE** | `/wines/{id}`    | Delete a wine                                                                         |

**Examples:**
- Pagination: `GET /wines?page=0&size=15`
- Sorting: `GET /wines?sort=price,desc`
- Filtering: `GET /wines?vintage=2019&color=Red`

### Database

#### **Table: `winery`**
| Column    | Type         | Constraints      | Description                 |
| :-------- | :----------- | :--------------- |:----------------------------|
| `id`      | `Integer`    | **PK**, Not Null | Unique identifier           |
| `name`    | `Varchar(255)`| Not Null         | Name of the vineyard        |
| `region`  | `Varchar(255)`| Not Null         | Geographic wine region      |
| `address` | `Varchar(255)`| Nullable         | Physical address            |

#### **Table: `wine`**
| Column      | Type         | Constraints           | Description                   |
| :---------- | :----------- | :-------------------- |:------------------------------|
| `id`        | `Integer`    | **PK**, Not Null      | Unique identifier             |
| `name`      | `Varchar(255)`| Not Null             | Name of the cuvée             |
| `vintage`   | `Integer`    | Not Null              | Year of production            |
| `color`     | `Varchar(255)`| Not Null             | Wine color (Red, White...)    |
| `type`      | `Varchar(50)` | Not Null             | Type (Still, Sparkling, etc.) |
| `price`     | `Float`      | Nullable, >= 0        | Unit price                    |
| `stock`     | `Integer`    | Nullable, >= 0        | Quantity available            |
| `winery_id` | `Integer`    | **FK** -> `winery(id)`| Foreign key to Winery table   |