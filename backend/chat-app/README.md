## Backend chat-app


### Root project
`chat-app`: Java project acting as parent Maven project for all microservices.
- Microservices will be added a child maven modules
- `<packaging>pom</packaging>` tells this is not an application project, but a parent project.

#### User Service
`chat-app/user-service`: Microservice for user service
- added as child Maven module of root project
- Sprint Boot 4.0.0 Application

#### Chat Service
`chat-app/chat-service`: Microservice for chat service
- same setup as user service
- Persistence: JPA (Java Persistence API) + Hibernate
- currently uses `h2` -  in-memory (non-persisting) database. Later replace with `PostgreSQL`.
