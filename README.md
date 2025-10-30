# E-Commerce Music Shop

This is a full-stack e-commerce application for a music shop, featuring a React frontend and a Spring Boot backend.

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security (JWT Authentication)
- Spring Data JPA

### Frontend
- React
- TypeScript
- Vite
- Tailwind CSS

### Database
- PostgreSQL
- Docker Compose

## Features

- User registration and login with JWT-based authentication.
- Role-based access control (CUSTOMER, MANAGER, ADMIN).
- RESTful API for managing products, users, and orders.
- A responsive frontend to browse products and view details.

## Getting Started

### Prerequisites

- JDK 21
- Node.js and npm
- Docker

### Setup & Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/nazarune/ecommerce-music.git
    cd ecommerce-music
    ```

2.  **Start the Database:**
    Run the database in a Docker container using the provided `docker-compose.yml` file.
    ```sh
    docker-compose up -d
    ```

3.  **Run the Backend:**
    Navigate to the `api` directory and run the Spring Boot application.
    ```sh
    cd api
    ./gradlew bootRun
    ```
    The API will be available at `http://localhost:8080/api`.

4.  **Run the Frontend:**
    Open a new terminal, navigate to the `frontend` directory, install dependencies, and start the development server.
    ```sh
    cd frontend
    npm install
    npm run dev
    ```
    The frontend application will be running on `http://localhost:5173`.

## API Endpoints

The backend exposes several REST endpoints under the `/api` context path.

For more details, the OpenAPI specification is available at `/v3/api-docs` and Swagger UI at `/swagger-ui/index.html` once the backend is running.

## TODO:

- Images for products
- Admin panel
- Category and filters