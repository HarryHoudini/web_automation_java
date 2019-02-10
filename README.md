# Web Automation with Java

Playground for web automation with:

1. Java
1. [Gradle](https://gradle.org/)
1. [TestNG](https://testng.org)
1. [Selenide](https://selenide.org/)
1. [Selenoid](https://github.com/aerokube/selenoid)

## Prerequisites

1. Install JDK 7+, e.g. [OpenJDK](https://openjdk.java.net/).
1. Install and configure [Gradle](https://gradle.org/).

### Selenoid prerequisites

1. Install [Docker](https://docs.docker.com/install/).
1. Install [Docker Compose](https://docs.docker.com/compose/install/).
1. Get browsers mentioned in `config/browsers.json`, e.g.: `docker pull selenoid/vnc:chrome_71.0`.
1. Get Selenoid Video Recorder: `docker pull selenoid/video-recorder:latest-release`.
1. Create selenoid docker network: `docker network create selenoid`.

## Run tests

### Simple run

1. `gradle cleanTest test`

### Run tests with Selenoid

1. `sudo -E docker-compose up`
1. Verify browsers list on [http://localhost:4444/status](http://localhost:4444/status).
1. `gradle cleanTest test -Dselenide.remote=http://localhost:4444/wd/hub`
1. *Optional:* Open [http://localhost:8080](http://localhost:8080/#/) to check Selenoid UI.