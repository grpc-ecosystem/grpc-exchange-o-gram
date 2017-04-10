
setlocal

cd /d %~dp0\..\..

@rem set location of protoc and grpc_csharp_plugin
set PROTOC=%UserProfile%\.nuget\packages\Google.Protobuf.Tools\3.2.0\tools\windows_x64\protoc.exe
set PLUGIN=protoc-gen-grpc=%UserProfile%\.nuget\packages\Grpc.Tools\1.2.2\tools\windows_x64\grpc_csharp_plugin.exe

@rem generate C# code
%PROTOC% proto/exchange_o_gram.proto -I proto --plugin=%PLUGIN% --csharp_out=client/csharp/ExchangeOGramClient --grpc_out=client/csharp/ExchangeOGramClient

endlocal