
gRPC Demo: Hands on building and debugging services
===================================

This demo will show you how to:
- Design an API for a service using protocol buffers (a simple photo sharing app) 
- Build gRPC service & client (server in Java, client in C#)
- Use the `grpc_cli` command line tool to explore services running on a server and invoke RPCs manually
- Extend the server with a new functionality (while maintaining backward compatibility)
- TODO: Run the gRPC service on Kubernetes

## Exchange-O-Gram

We will be building a simple social application that allows posting text and photo on your personal wall.

![Diagram](exchangeogram-diagram.png)

TODO: explain the service more

## Structure of this repository

This demo is split into several steps:
- `1-wallservice` Initial version of the Exchange-O-Gram app.
- `2-mediaservice` Adding new functionality to the original app.

The recommended way to try this demo is to work through the steps in order.

## Prework 

### Create Google Cloud Project

TODO

### Authenticate with Google Cloud

```
$ gcloud config set project YOUR_PROJECT_ID
$ gcloud auth application-default login
```

### Initialize Spanner DB

Use `sql/exchange_o_gram.sql` to create the DB schema.
