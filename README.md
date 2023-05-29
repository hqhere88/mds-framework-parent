[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

## Prerequisites

The project can be imported into the IDE of your choice, with Java 8 installed, as a Maven project.

## Project Structure

The project consists of the following three modules:

- parent project: common metadata and configuration
- mds-framework-dependencies: dependencies (pom)
- mds-framework-api: rest api application  (war)                        http://localhost:8008/api/users
- mds-framework-ui: main application module that includes views (war)   http://localhost:8002
- mds-framework-module: sub module (pom)
  - mds-framework-common: common (jar)
  - mds-framework-controller: controller (jar)
  - mds-framework-service: service (jar)
  - mds-framework-dto: dto for view (jar)
  - mds-framework-entity: entity for dao (jar)
  - mds-framework-dao: dao (jar)
- mds-framework-user-defined-component: sub module for user-defined components (jar)


## Workflow

To compile the entire project, run "mvn install" in the parent project.

Other basic workflow steps:

- getting started
- compiling the whole project
  - run `mvn install` in parent project
  - run `mvn install` in mds-framework-ui project
- developing the application
  - edit code in the ui module
  - open http://localhost:8002
