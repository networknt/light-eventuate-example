# Light-eventuate-4j example-- Account Money Transfer

Account Money Transfer example is build on light-4j, light-rest-4j and light-eventuate-4j which use event sourcing as major idea to handle event process in multi microservice,
Applications consist of loosely coupled components that communicate using events. Using an event-driven architecture to achieve data consistency - rather than using traditional distributed transaction to maintain database consistency this application uses an eventually consistent, event-driven approach
These components can be deployed either as separate services or packaged as a monolithic application for simplified development and testing.



# Structure of the example

Modules:

common:  common module for the application. - the domain logic consists of Domain-Driven Design (DDD) aggregates that using event sourcing.
command:  command side common components, include command, services
query:   query side common components, include command, services
e2etest: end to end test module


There are the following services:

Customers Service - REST API for creating customers
Accounts Service - REST API for creating accounts
Transactions Service - REST API for transferring money
Customers View Service - subscribes to events and updates a MongoDB View, and provides an API for retrieving customers
Accounts View Service - subscribes to events and updates a MongoDB View, and provides an API for retrieving accounts

# Building and running the microservices

Assume you created a directory named networknt under user directory.

Checkout related projects.

```
cd ~/networknt
git clone git@github.com:networknt/light-4j.git
git clone git@github.com:networknt/light-rest-4j.git
git clone git@github.com:networknt/light-codegen.git
git clone git@github.com:networknt/light-eventuate-4j.git


## Prepare workspace

Go into the projects folder above, and build the project with maven

```
mvn clean install

```

Get the example project from github:

git clone git@github.com:networknt/light-eventuate-example.git

cd ~/networknt/light-eventuate-example/account-management

mvn clean install




# Steps to start event-store and microservice


1. Go to light-eventuate-4j root folder:

    -- cd cd ~/networknt/light-eventuate-4j

2. Run docker compose file to start event store and CDC service:

    -- docker-compose up
    -- run docker-compose -f docker-compose-cdcservice.yml up

3. Go to account money transfer example root folder:

    -- cd ~/networknt/light-eventuate-example/account-management

4.  Run docker compose to start microservices

   -- docker-compose up



# Test to verify result:

Create a new customer:

  curl -X POST \
    http://localhost:8083/v1/createcustomer \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -d '{"name":{"firstName":"Google11”,”lastName":"Com"},"email":"aaa1.bbb1@google.com","password":"password","ssn":"9999999999","phoneNumber":"4166666666","address":{"street1":"Yonge St","street2":"2556 unit","city":"toronto","state":"ON","zipCode":"Canada","country":"L3R 5F5"}}'

View the new customer:

http://localhost:8084/v1/customers/aaa1.bbb1@google.com


