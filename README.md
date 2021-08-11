# CampusShops
A micro-service architecture program that offering functions including product publish, sale, user management, etc.
We choose the Nginx as our API entrance, static files server, and webserver. BFF (Back-end for front-end) offers us an excellent platform to manage different essential microservices and integrate responses from APIs.
The HTTP protocol is the primary way of communication of services in this project, not the RPC.

# Architecture
![architecture](https://github.com/ChenhuaFan/campusShops/blob/master/documents/imgs/image.png)

# files structure
- /src
  - /www | for website
    - /static
    - /pages
    - /...
  - /authService  | for user authorization service
    - /...
  - /apiGateway
    - /...
  - /bff  | for micro service modules
    - /...
  - /Services
    - /users
      - /configs
      - /...
    - /...
- README.md
