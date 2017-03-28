#!/bin/bash

export GRPC_DEFAULT_SSL_ROOTS_FILE_PATH=$HOME/grpc-service-demo/certs/ca.pem

grpc_cli ls -l localhost:8433

grpc_cli call localhost:8433 exchange_o_gram.WallService.GetWallPosts 'username: "testuser"'