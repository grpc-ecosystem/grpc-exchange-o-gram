package io.grpc.demo.exchange_o_gram;

import java.io.File;
import java.util.UUID;

final class Utils {

  static final int SERVER_PORT = 8433;

  static final String INSTANCE_ID = "exchange-o-gram";
  static final String DATABASE_ID = "exchange-o-gram";

  static final File TLS_CERT_FILE = new File("server.pem");
  static final File TLS_CERT_KEY = new File("server.key");

  static long newId() {
    return UUID.randomUUID().getLeastSignificantBits();
  }
}
