![CD | Maven | Docker | AWS EC2](https://github.com/reirasdev/reservation-api/workflows/CD%20%7C%20Maven%20%7C%20Docker%20%7C%20AWS%20EC2/badge.svg?branch=master)

### Reservation API
This application is a fragment of an API which belongs to a full solution oriented to connect travelers, homeowners and property managers by providing an easy and safe way to book flats, beach houses and every kind of space in between.</br>
The purpose of this repository is to demonstrate a way to provide Continuous Deployment using Docker containers.

The application exposes its features through a RESTful API built on Java with Spring Boot, Spring Security, Spring Data and MongoDB. Swagger was used for documentation. Tests were written using auxiliary APIs such as JUnit, REST Assured and Flapdoodle Embedded MongoDB.

For every push on master branch a pipeline is triggered on GitHub Actions.</br>
It guarantees Continuous Deployment by performing the following tasks:
- [x] Run tests, compile and package app with Maven
- [x] Build Docker image - [Dockerfile](https://github.com/reirasdev/reservation-api/blob/master/Dockerfile)
- [x] Publish Docker image on DockerHub** - https://hub.docker.com/r/reirasdev/reservationmicroservice</br>***GitHub push SHA is used to tag Docker image so the commit can be easily correlated to its image on DockerHub*
- [x] Up container, based on DockerHub publication, using Docker Compose on AWS EC2 instance - [docker-compose.yml](https://github.com/reirasdev/reservation-api/blob/master/docker-compose.yml)

The deployment environment is an instance of AWS EC2 which had Docker installed from scratch.</br>
The Docker composition built for this application connects to a MongoDB instance via bridge network.</br>
In terms of Docker, a bridge network uses a software bridge which allows containers running on the same Docker daemon to communicate while providing isolation from containers which are not connected to that same bridge network.</br>
The Docker container running MongoDB and starting the bridge network was built using [docker-compose-mongo.yml](https://github.com/reirasdev/reservation-api/blob/master/docker-compose-mongo.yml).

The deployed application can be reached here:</br>
http://ec2-18-228-235-65.sa-east-1.compute.amazonaws.com:8080/swagger-ui.html
