Reactive Task Manager
--

Sample application to try new Spring 2.0 and Spring WebFlux features.

#### Task list:

- [x] Create a team 
- [x] Create a task
- [ ] Update new task on client
- [ ] Manage async operation status on client


#### Target:

Create a simple ```CRUD``` to manage a team tasks.

Use Flux and Server Side Event to load new task on client side.

 
#### Stack:

* Server Side Events
* Spring WebFlux
* Spring Boot 2.0
* Spring Data 2.0 M1-Kay (MongoDB)

#### Usage:

1. Create Data Base

```docker run --name reactive-task-mongo -e "MONGODB_DBNAME=reactivedb" -d mongo```

2. Import project on your favorite IDE and run as normal Spring Boot application.