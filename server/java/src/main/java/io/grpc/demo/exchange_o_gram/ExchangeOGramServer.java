package io.grpc.demo.exchange_o_gram;

import static io.grpc.demo.exchange_o_gram.Utils.SERVER_PORT;
import static io.grpc.demo.exchange_o_gram.Utils.TLS_CERT_FILE;
import static io.grpc.demo.exchange_o_gram.Utils.TLS_CERT_KEY;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

public class ExchangeOGramServer {

  public static void main(String... args) throws Exception {
    Server server =
        ServerBuilder.forPort(SERVER_PORT)
        .addService(new WallService())
        .addService(new MediaService())
        // Use TLS
        .useTransportSecurity(TLS_CERT_FILE, TLS_CERT_KEY)
        // Use Reflection
        .addService(ProtoReflectionService.newInstance())
        .build();

    server.start();

    System.out.println("Server started on port: " + SERVER_PORT);

    server.awaitTermination();
  }
}
