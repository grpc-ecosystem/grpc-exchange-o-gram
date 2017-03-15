
gRPC Demo: Hands on building and debugging services
===================================

## Authenticate with Google Cloud

```
$ gcloud config set project grpc-kubecon-demo2017
$ gcloud auth application-default login
```

## Server
From the `./server/java` directory type:
```
$ ./gradlew run
Server started on port: 8181
```

