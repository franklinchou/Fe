

### Starting a docker container

Issue:

```
docker run -d --name mongo-sandbox -p 27017:27017 -v ~/data:/data/db mongo:latest
```

