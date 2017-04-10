#!/bin/bash
set -ex
cd $(dirname $0)/../..

PROTOC=$HOME/.nuget/packages/Google.Protobuf.Tools/3.2.0/tools/linux_x64/protoc
PLUGIN=protoc-gen-grpc=$HOME/.nuget/packages/Grpc.Tools/1.1.0/tools/linux_x64/grpc_csharp_plugin
$PROTOC proto/exchange_o_gram.proto -I proto --plugin=$PLUGIN --csharp_out=client/csharp/ExchangeOGramClient --grpc_out=client/csharp/ExchangeOGramClient
