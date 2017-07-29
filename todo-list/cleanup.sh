#!/bin/bash

# The following commands remove all todolist services containers and images in order to start a brand new build.
# It should be called if you have changed any code in one of services.

docker ps -a | awk '{ print }' | grep todolist_ | awk '{print $1}' | xargs -I {} docker rm {}
docker images | awk '{ print }' | grep todolist_ | awk '{print $3}' | xargs -I {} docker rmi {}
