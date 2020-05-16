The aim of the project is to implement microservices architecture.
The solution is a bunch of services that communicate to each other allowing create, store and modify Goals. There is a special configuration centre and couple of services for fault tolerance.
To speed up deployment containerization is used. 

# Usage

## Navigation
This project is a part of university's practice(Modern Methodologies And Development Technologies of Software Development) that is split to few parts. To navigate, use tags:

```
# list all tags
git tag

# move to specific practice. for instance 3 lab
git checkout v3.0
```

Basically, newer versions contain all previous practices. 

## Deployment

```
git clone https://github.com/KStasi/SpringCloudProject.git
cd SpringCloudProject
docker-compose build
```

## Run

For practices 3-4:

```
sudo docker-compose up --scale client=2
```

For practice 5:

```
sudo docker-compose up --scale client=2 --scale consumer=3
```

## Testing

Read `test.http` for manual testing.

# Open ports

Eureka Server URL: http://localhost:8761
Service URL (instance 1): http://localhost:8081
Service URL (instance 2): http://localhost:8082
Api Gateway URL: http://localhost:9000
Config Server: http://localhost:8888
Kafka Consumer URL (instance 1): http://localhost:8034
Kafka Consumer URL (instance 2): http://localhost:8035
Kafka Consumer URL (instance 3): http://localhost:8036

Kafka URL: http://localhost:9092
Prometheus URL: http://localhost:9090, http://localhost:9080
Zookeeper URL: http://localhost:2181
Rabbit URL: http://localhost:5672, http://localhost:15672
Mongo URL: http://localhost:27017
Grafana URL: http://localhost:3000
