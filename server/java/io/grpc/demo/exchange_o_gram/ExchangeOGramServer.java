package io.grpc.demo.exchange_o_gram;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class ExchangeOGramServer {

  public static void main(String... args) throws IOException, InterruptedException {
    int port = 8181;
    Server server = ServerBuilder
        .forPort(port)
        .addService(new MediaService())
        .addService(new WallService())
        .build();
    server.start();
    System.out.println("Server started on port: " + port);
    server.awaitTermination();
  }
}
