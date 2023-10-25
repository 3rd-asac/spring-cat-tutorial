# Docker_Nginx_Spring-Gradle_Postgres

Docker-multi-compose-example

1. Nginx
2. Gradle-Spring-JPA-CRUD
3. Postgres

[Open in Docker Dev Environments](https://open.docker.com/dashboard/dev-envs?url=https://github.com/ooMia/spring-cat-tutorial/tree/ServerD/Mia/Project/)

### GET http://localhost:8080/api/v1/client

### POST http://localhost:8080/api/v1/client/register
body:
Content-Type: application/json
```JSON
{ "id": "updateMe", "privateCode": "1234", "nickName": "beforeUpdate" }
```
### POST http://localhost:8080/api/v1/client/register
body:
Content-Type: application/json
```JSON
{ "id": "deleteMe", "privateCode": "1234", "nickName": "beforeDelete" }
```

### DELETE http://localhost:8080/api/v1/client/deleteMe?privateCode=1234

### PUT http://localhost:8080/api/v1/client/updateMe?privateCode=1234&nickName=afterUpdate
