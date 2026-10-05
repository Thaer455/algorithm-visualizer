# Application Architecture

## Overview

The application is split into a React frontend, a Spring Boot backend, a PostgreSQL database, and a logically independent algorithm engine. The frontend and backend are separate applications. They communicate through REST APIs; the browser does not connect directly to the database.

The current repository reflects the frontend, backend, and local PostgreSQL development service. The algorithm engine is an architectural responsibility for future work and is not implemented yet.

## Frontend

The frontend is the React, TypeScript, and Vite application in `frontend/`. Its responsibilities are:

- Render the application interface and reusable UI components.
- Handle user interaction, including algorithm selection and visualization controls.
- Visualize algorithm execution data returned through the backend API.
- Use the API client in `frontend/src/api/` to communicate with backend REST endpoints.

The frontend owns presentation and interaction. It does not implement algorithm execution or access PostgreSQL directly.

## Backend

The backend is the Java 21 and Spring Boot application in `backend/`. Its responsibilities are:

- Expose REST endpoints to the frontend.
- Validate and handle API requests, coordinate application logic, and return API responses.
- Call algorithm-related services, including the algorithm engine, where appropriate.
- Mediate access to persistent data through backend services and persistence components.

The backend is the boundary between the frontend and server-side capabilities. REST payloads carry request and response data; they do not expose UI components to backend code.

## Database

PostgreSQL is the relational database provided for the application and configured as a development service in `docker/docker-compose.yml`. Its responsibility is persistent application data. The backend owns database access; frontend code communicates with stored data only through backend REST APIs. Database credentials and connection configuration belong in external configuration rather than source code.

The current backend configuration does not yet define application persistence. Database-backed features can be added when a task requires them.

## Algorithm Engine

The algorithm engine is responsible for algorithm implementations, execution steps, and algorithm statistics. It should provide reusable execution data and remain independently testable without the frontend or visualization layer.

The engine must not import, call, or manipulate frontend components. The frontend visualizes execution data through its own components; it does not control algorithm internals. Where the backend coordinates execution, it should depend on the engine through backend application services or a clear interface, rather than coupling algorithms to REST controllers or UI code.

The engine is part of the planned architecture and has not yet been implemented in the current project structure.

## REST Communication

The frontend calls backend REST endpoints over HTTP using its API client. For example, `frontend/src/api/backendApi.ts` requests `GET /api/health`, which is handled by the Spring REST controller in `backend/src/main/java/com/thaer/backend/HealthController.java`. The backend returns a JSON response, and the frontend validates and uses that response in the interface.

The same boundary applies to future algorithm operations: the frontend sends input and execution requests to backend endpoints; backend application logic coordinates the algorithm engine and returns serializable execution data; frontend visualization components render that data. The browser does not call the engine directly or connect to PostgreSQL.

## Dependency Boundaries

- Frontend components depend on frontend presentation and API client code, not backend Java classes or database internals.
- Backend REST controllers and application services may use the algorithm engine and persistence components through backend boundaries.
- The algorithm engine depends on its algorithm and execution domain, not frontend UI or visualization components.
- PostgreSQL is accessed by backend persistence code, never directly by frontend components.
