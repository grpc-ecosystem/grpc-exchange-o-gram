package io.grpc.demo.exchange_o_gram;

import static io.grpc.demo.exchange_o_gram.Utils.TLS_CERT_FILE;
import static io.grpc.demo.exchange_o_gram.Utils.TLS_CERT_KEY;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import java.io.IOException;

public class ExchangeOGramServer {

  public static void main(String... args) throws IOException, InterruptedException {
    int port = 8433;

    Server server = ServerBuilder.forPort(port)
        .addService(new WallService())
        .addService(new MediaService())
        // Remove before the demo, and then show how easy it is to add reflection.
        .addService(ProtoReflectionService.newInstance())
        .useTransportSecurity(TLS_CERT_FILE, TLS_CERT_KEY)
        .build();
    server.start();
    System.out.println("Server started on port: " + port);
    server.awaitTermination();
  }
}
