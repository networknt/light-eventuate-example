# light-eventuate-example

The Todo List application is the entry point POC example application for the Light Eventuate. It illustrates how you can use the platform to write an application with a microservices architecture that uses Event Sourcing and Command Query Responsibility Segregation (CQRS). The Todo List application lets users maintain a todo list.

The Todo List application is a Java application built on Light-Java platform by using Event Sourcing based programming model. Todos are implemented by an Event Sourcing-based TodoAggregate. The aggregate's events are persisted in the Eventuate event store. The application also maintains a materialized view of the data in MySQL.