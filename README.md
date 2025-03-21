# Reborn Market - Backend

## Table of Contents
- [Project Description](#project-description)
- [Reborn Market](#-reborn-market)
- [Philosophy](#-philosophy)
- [Key Features](#key-features)
- [Project Structure](#project-structure)
- [Api Endpoints](#api-endpoints)
- [Working Examples](#working-examples)
- [Technologies Used](#technologies-used)
- [Installation and Setup](#installation-and-setup)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Setting Up Docker Compose](#-setting-up-docker-compose)
- [Unit and Acceptance Tests](#unit-and-acceptance-tests)
---
## 💡Project Description
We have developed this project with the specifications that were given to us at the beginning of the F5 Hackathon. Following the theme of the event, we have developed a market place. On this website you will be able to sell products related to parenting, from clothes to toys... We have implemented a basic auth, a small api and a database with PostgreSQL and Docker.

---

# 🌱 Reborn Market

Reborn Market is a unique marketplace designed for parents who want to give a second life to children's items, as well as discover new products. Instead of throwing away clothes, toys, or furniture as their children grow, parents can sell, donate, exchange, or even buy new items from trusted vendors. Our goal is to promote sustainability, affordability, and convenience by allowing both companies and individuals to participate.

## 🌍 Philosophy

The name "Reborn" reflects our mission to breathe new life into pre-loved items and reduce waste, while also offering new, high-quality products for families in need. We believe that every product deserves a second chance, and that families should have easy access to affordable, sustainable, and even free items. The platform supports a wide range of price segments, from high-quality branded goods to free items donated by other parents, as well as brand-new products from trusted vendors.

---

## 🚀Key Features
### 📜 Product Management
- Create a new product
- Update a exists product
- Delete a exists product
### 👤 User Management
- Basic Auth
---
## 📂Project Structure
The project follows the principle of separation of features.

![img](https://github.com/user-attachments/assets/ebb86a11-f45b-45dd-a75c-620c9af31ede)

---
## 📦API Endpoints
URL: http://localhost:8080/api/v1/ + endpoint

### User Endpoints
| Method | Endpoint         | Description           |
|--------|------------------|-----------------------|
| GET    | `/private/users` | Retrieve all users.   |
| POST   | `/register`      | Register a new user.  |
| DELETE | `/user/{id}`     | Delete an user by ID. |


### Product Endpoints
| Method | Endpoint         | Description             |
|--------|------------------|-------------------------|
| GET    | `/products`      | Retrieve all products.  |
| POST   | `/products`      | Register a new product. |
| PUT    | `/products/{id}` | Update product details. |
| DELETE | `/products/{id}` | Delete a product by ID. |

### Category Endpoints
| Method | Endpoint         | Description              |
|--------|------------------|--------------------------|
| GET    | `/category`      | Retrieve all categories. |
| POST   | `/category`      | Register a new category. |
| PUT    | `/category/{id}` | Update category details. |
| DELETE | `/category/{id}` | Delete a category by ID. |


---
## ✍️Working examples
### Create product
**POST** `http://localhost:8080/api/v1/products`

```json
{
   "name": "Lego",
   "image": "lego.jpg",
   "description": "Lego description",
   "price": 1200.50,
   "age": 1,
   "condition": "USADO",
   "userId": 1,
   "categoryId": 2
}
```

---
## 🛠️Technologies Used
- **Java 21**: Programming language.
- **Spring Boot**: Framework for building REST APIs.
- **Maven**: Build tool.
- **PostgreSQL**: Database, deployed using Docker.
- **Docker**: Used for containerizing the PostgreSQL database.
- **Docker Compose**: Orchestrates multi-container Docker applications.
- **H2 Database**: In-memory database for temporary data storage.

---

## ⚙️Installation and Setup

1. **Clone the repository**:

```bash
git clone https://github.com/HackSisters/reborn-market-backend.git
```

2. **Configure the database in the application properties**:
- This project has two different configurations for databases:
- Test Environment (H2 in-memory database): Configured in resources/application-test.yml.
- Development Environment (PostgresSQL via Docker): Configured in resources/application-dev.yml.
- For testing, the application-test.yml contains the configuration for an in-memory H2 database.
- For development, the application-dev.yml contains the configuration for a PostgreSQL database deployed using Docker.

**Here you can see database architecture**
![img_1](https://github.com/user-attachments/assets/94d4e537-3d4e-4ffa-8a55-650dbf099d08)


3**Run the application**:
   To run the application, first, make sure you have the correct profile active. You can specify the active profile by adding the following to your application.properties (or application-dev.yml and application-test.yml for environment-specific settings):
- For test environment:
```bash
mvn spring-boot:run -Dspring.profiles.active=test
```
- For development environment:
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```
---
### 🚀 CI/CD Pipeline
This project uses GitHub Actions for continuous integration and continuous deployment (CI/CD). There are two separate pipelines configured for testing and development environments:

- **Test Pipeline (H2 Database)**: This pipeline runs the unit and integration tests using an in-memory H2 database. It ensures that the project works correctly in a test environment.

- **Development Pipeline**: This pipeline runs in a development environment, where the project is built and deployed with a PostgreSQL containerized database using Docker. It tests the application in a more realistic environment.

Both pipelines ensure that any changes made to the repository are properly tested and validated before being deployed.


---
### 🛠️ Setting Up Docker Compose
*To run the PostgreSQL database with Docker for the development environment, you can use Docker Compose.*

1. Install Docker if you haven’t already.

2. Create a Docker Compose configuration: In your project, there should be a docker-compose.yml file for configuring and starting the PostgreSQL container.

Example docker-compose.yml:
```yaml
services:
  db:
    image: postgres:17
    container_name: reborn_data_base
    environment:
      POSTGRES_USER: ${DB_USER}
      POSTGRES_PASSWORD: ${DB_PASSWORD}
      POSTGRES_DB: reborn_data_base
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - springboot_network

volumes:
  postgres_data:

networks:
  springboot_network:
```
3. Run Docker Compose:

After setting up docker-compose.yml, you can start the PostgreSQL container with the following command:

```bash
docker-compose up
```
This command will download the necessary PostgreSQL image, start the container, and expose the database on port 5432.

4. Run the application with the development profile:

After PostgreSQL is up and running, you can start the application in development mode with:

```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

---

### 🧪Unit and Acceptance Tests
![img_2](https://github.com/user-attachments/assets/fedd2f5b-4d2c-445a-a3ab-4ba5c2a2ef27)
![img_3](https://github.com/user-attachments/assets/5bbcc62d-7a87-434e-b991-a2c47ec2b914)

---

---

#### Project made by:

-  [Yuliia Bila](https://github.com/YuliiaBi1a) 
- [Marta Bernardo](https://github.com/MartaBernardoZamora)
- [Israel Espin](https://github.com/iespin)
- [Yuliia Martynovych](https://github.com/yuliia-martynovych)
- [Emma Lanza](https://github.com/emmalanza)
- [Nadiia Alaieva](https://github.com/tizzifona)
- [Angie Rodas](https://github.com/angiehelensanchez)

like part of Hackaton F5
