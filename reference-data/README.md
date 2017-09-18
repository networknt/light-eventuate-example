# Light-Eventuate-4j-- reference data Service

Reference Data Service is the service for manager reference data (generally the dropdown value for the UI) for industry. Reference Data Service is microservice build upon the light-4j;
And it use light-eventuate-4j for event sourcing control.


Basic features for Reference Data Service:.

Add reference table (with reference data or without reference data);

Modify reference table;

Delete referecen table;

Add reference value to specified reference table;

Modify reference value;

Delete referecen value;

Add referecen value relationship;

Get all hosts as a list of string;

Get all reference tables for specified host;

Get all reference tables;

Get reference table name as a list of string for specified host;

Get reference table by specified host and reference table name;

Get reference table by specified reference table id;

Get reference value by specified reference value id;




## Value Objects:

ReferenceTable;

ReferenceValue;

ValueLocale;

Relation;




## Build project and run verification.


Get the light-eventuate-example project from github:

```
cd ~/networknt
git clone git@github.com:networknt/light-eventuate-example.git

https://github.com/networknt/light-eventuate-example.git
cd ~/networknt/light-eventuate-example/reference-data
mvn clean install
```




 Run docker compose to start microservices

   cd ~/networknt/light-eventuate-example/reference-data

   -- docker-compose up





# Test to verify result:

— create reference table:

```
curl -X POST \
  http://localhost:8081/v1/refTable \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"tableName":"COUNTRY","tableDesc":"country ref code","host":"CIBC","active":true,"editable":true,"common":true,"values":[]}'
```

--- System will return new reference table id



— -- create reference value for specified reference table:

```
curl -X POST \
  http://localhost:8081/v1/refValue \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
   -d '{"tableId":"0000015e95834e32-0242ac1200070001","valueCode":"ccccccccc","startTime":"2017-09-18T13:10:01","displayOrder":1,"active":true,"locales":[{"language":"En","valueDesc":"test value"}],"relations":[]}
'
```


--- get all reference data for specified host name
curl -X GET \
  http://localhost:8082/v1/refTables/CIBC \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'