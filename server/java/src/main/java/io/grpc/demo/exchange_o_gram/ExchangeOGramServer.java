package io.grpc.demo.exchange_o_gram;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class ExchangeOGramServer {

  public static void main(String... args) throws IOException, InterruptedException {
    int port = 8181;
    Server server = ServerBuilder
        .forPort(port)
        .addService(new WallService())
        .addService(new MediaService())
        .build();
    server.start();
    System.out.println("Server started on port: " + port);
    server.awaitTermination();


// I used this to do a quick test of the functionality
//    WallService ws = new WallService();

//    WallPost post = WallPost.newBuilder().setUsername("maxmustermann").setCaption("hello world")
//        .setMediaId(MediaId.newBuilder().setId(100).build())
//        .build();
//    ws.postToWall(PostToWallRequest.newBuilder().setPost(post).build(), null);
//    System.out.println("Wrote to spanner.");
//
//    GetWallPostsRequest request = GetWallPostsRequest.newBuilder().setUsername("maxmustermann").build();
//    ws.getWallPosts(request, null);

    MediaService ms = new MediaService();

//    Random r = new Random();
//    byte[] data = new byte[1048500];
//    r.nextBytes(data);
//
//    UploadImageRequest request =
//        UploadImageRequest.newBuilder().setImage(Image.newBuilder().setMimetype("jpeg").setData(
//        ByteString.copyFrom(data)).build()).build();
//    long start = System.nanoTime();
//    ms.uploadImage(request, null);
//    long end = System.nanoTime();
//    System.out.println("uploaded image: " + (end - start) / 1000);

//    DownloadImageRequest request =
//    DownloadImageRequest.newBuilder().setId(MediaId.newBuilder().setId(-6872392298156931001L).build()).build();
//    ms.downloadImage(request, null);
  }
}