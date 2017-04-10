Building WallService
====================

## Protocol Buffers
`./proto/exchange_o_gram.proto` contains definitions of `WallService` and all relevant protobuf messages (e.g. `WallPost`, `PostToWallRequest`, `PostToWallResponse`).

## Prerequisites
- Java 8 SDK
- Visual Studio 2017 (Windows) or [dotnet SDK](https://www.microsoft.com/net/core) (Linux, Mac or Windows) installed.

## Generating Java code from `.proto`
The Java code (for the server) will be generated automatically during gradle build phase. 

## Generating C# code from `.proto`

(the generated code is already checked-in to the github repository, so you can skip this step if you want to)

From the `./client/csharp` directory type:
```
$ dotnet restore ExchangeOGramClient

# On Linux or Mac
$ ./generate_proto_csharp.sh

@rem On Windows
> generate_proto_csharp.bat
```

## Build Server

From the `./server/java` directory type:
```
$ ./gradlew run
Server started on port: 8433
```


## Build Client

```
$ dotnet restore
$ dotnet run
Now listening on: http://localhost:5000
Application started. Press Ctrl+C to shut down.
```

Visit the URL in your browser.

