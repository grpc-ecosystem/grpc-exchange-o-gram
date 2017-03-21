import io.grpc.ManagedChannel;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse;
import io.grpc.demo.exchange_o_gram.WallServiceGrpc;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by buchgr on 3/22/17.
 */
public class Main {

 public static void main(String... args) throws Exception {

   SslContextBuilder sslContextBuilder
       = GrpcSslContexts
       .forClient()
       .trustManager(new File("../../certs/ca.pem"))
       .sslProvider(SslProvider.OPENSSL);

   ManagedChannel ch = NettyChannelBuilder.forAddress("localhost", 8433)
       .negotiationType(NegotiationType.TLS)
       .sslContext(sslContextBuilder.build())
       .channelType(NioSocketChannel.class)
       .build();

   WallServiceGrpc.WallServiceBlockingStub stub =
       WallServiceGrpc.newBlockingStub(ch);
   Iterator<GetWallPostsResponse> r = stub.getWallPosts(GetWallPostsRequest.newBuilder().setUsername("testuser").build());
   while (r.hasNext()) {
     System.out.println(r.next().getPost().getCaption());
   }

   ch.awaitTermination(1, TimeUnit.HOURS);
 }
}
