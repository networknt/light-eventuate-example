# light-eventuate-example

The Todo List application is the entry point POC example application for the Light Eventuate. It illustrates how you can use the platform to write an application with a microservices architecture that uses Event Sourcing and Command Query Responsibility Segregation (CQRS). The Todo List application lets users maintain a todo list.

The Todo List application is a Java application built on Light-Java platform by using Event Sourcing based programming model. Todos are implemented by an Event Sourcing-based TodoAggregate. The aggregate's events are persisted in the Eventuate event store. The application also maintains a materialized view of the data in MySQL.

#Building and running the application

Command side unit test.

On command side, run TodosPostHandlerTest test class on command-service module. This will verify command side service which pass TodoInfo as Restful request body.

Integration test steps:

1. config local mysql Database, run the DDL script under: \mysql\mysql_ddl.sql

2.change the service.yml config file in query-service module to set local datasource

3.under query-service, start test server by run command line: mvn exec:exec
 
 since EventuateClientStartupHookProvider already added into the META-INFO\service\com.networknt.server.StartupHookProvider file:
 # config event handle registration
 com.networknt.eventuate.client.EventuateClientStartupHookProvider
 System will startupHookProvide to start event handler processor

4. open brower, and type in url: http://localhost:8082/v1/todos
 
  -- this url will trigger a http request to TodosGetHandler, in the TodosGetHandler, system use command side service to create a todo event; the new create todo event will be saved into event store and publish out. At same time, event handle process will subscrible the event and create a todo based on the event and save the todo object to query side todo table. And then the TodosGetHandler will use the query side service to get ALL todo list to display on UI.
  
  

