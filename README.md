# Web Automation with Java

Playground for web automation with:

1. Java
1. [TestNG](https://testng.org)
1. [Selenide](https://selenide.org/)
1. [Selenoid](https://github.com/aerokube/selenoid)

## Prerequisites

1. Install JDK 7+.
1. Download [Selenoid](https://github.com/aerokube/selenoid/releases) and add executables to PATH.
1. Get browsers mentioned in `config/browsers.json`, e.g.: `docker pull selenoid/vnc:chrome_71.0`.

## Run tests

### Simple run

1. `gradlew cleanTest test`

### Run tests with Selenoid

1. Run selenoid with `./selenoid` on *nix or `selenoid.exe` on Windows.
1. Verify browsers list on `http://localhost:4444/status`.
1. `gradlew cleanTest test -Dselenide.remote=http://localhost:4444/wd/hub`