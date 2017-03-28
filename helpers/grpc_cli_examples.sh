#!/bin/bash
# Did you set path to your CA?
# export GRPC_DEFAULT_SSL_ROOTS_FILE_PATH=$HOME/grpc-service-demo/certs/ca.pem

grpc_cli ls -l --enable_ssl demo-linux1:8433

grpc_cli call --enable_ssl demo-linux1:8433 exchange_o_gram.WallService.GetWallPosts 'username: "testuser"'
