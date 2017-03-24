package io.grpc.demo.exchange_o_gram.spanner;

import java.io.File;
import java.util.UUID;

public final class Utils {

  public static final int SERVER_PORT = 8433;

  public static final String INSTANCE_ID = "exchange-o-gram";
  public static final String DATABASE_ID = "exchange-o-gram";

  public static final File TLS_CERT_FILE = new File("server.pem");
  public static final File TLS_CERT_KEY = new File("server.key");

  public static long newId() {
    return UUID.randomUUID().getLeastSignificantBits();
  }
}
