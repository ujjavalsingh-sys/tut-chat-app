# tut-chat-app

This is a tutorial chat app meant to cover basics of creating an end-to-end chat application.

## Getting started with development

### Software required

- Docker: For container deployment.
- VS Code: For frontend react app development in Typescript.
- IntelliJ: For backend spring boot microservice development in Java.

### Steps

1. `git clone` this repo.
2. Install Docker for container deployment.
3. Install `mkcert` for https support. Run `mkcert -install` to install local CA required for SSL using certificates in `certs/` folder. This is a one time activity.
4. Backend (just running) -
    1. `cd backend\chat-app`
    2. `docker-compose up --build`
    3. (optional) Press `d` to detach docker process from terminal. It will keep running in background.
5. Backend (debugging) -
    1. In Docker Desktop, start `postgres` container only and stop all other containers.
    2. Download and install IntelliJ.
    3. Open `backend\chat-app` in IntelliJ.
    4. From topbar, run/debug all 3 services simultaneously - `chat-service`, `gateway-service` and `user-service`. This is necessary because Gateway uses different URLs in docker and dev environment for all microservices -
        1. Docker environment uses container urls, e.g. "http://user-service:8082".
        2. Development environment uses dev urls, e.g. "http://localhost:8082".
6. Frontend:
    1. `cd frontend/chat-app`
    2. `npm install`
    3. `npm run dev`
7. Open browser and navigate to "https://localhost:5173" (note the https). It should redirect to "https://localhost:5173/login" after sometime.

At this time, the application has been setup successfully for development.

### Environment Variables

For Backend, there are 2 environments and these are where environment variables are defined -
- Docker: Environment variables are defined in `docker-compose.yml` "environment" property.
- Development: Environment variables are defined in IntelliJ config files `backend/chat-app/.idea/runconfigurations/<microservice>.xml` for each microservice.

For Frontend, `frontend/chat-app/.env` contains development environment variable, accessible via `import.meta.env` object in React Vite app.
