
gRPC Demo: Hands on building and debugging services
===================================

## Authenticate with Google Cloud

```
$ gcloud config set project grpc-kubecon-demo2017
$ gcloud auth application-default login
```

## Client
Prerequisites: Visual Studio 2017 (Windows) or [dotnet SDK](https://www.microsoft.com/net/core) (Linux, Mac or Windows) installed. 

From the `./client/csharp/ExchangeOGramClient` directory type:
```
$ dotnet restore
$ dotnet run
```


## Server
From the `./server/java` directory type:
```
$ ./gradlew run
Server started on port: 8181
```

