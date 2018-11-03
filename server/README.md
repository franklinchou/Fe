# Fe-Server

## Deployment

1. Run mongo backend: 
* `docker run --name fe-mongo -p 27017:27017 mongo:latest`
* `docker start fe-mongo`

2. Start server:
* `sbt run` from the server directory (same directory as this README)