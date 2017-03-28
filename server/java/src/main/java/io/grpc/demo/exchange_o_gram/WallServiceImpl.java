package io.grpc.demo.exchange_o_gram;

import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse;

import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.WallPost;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.WallPostId;
import io.grpc.demo.exchange_o_gram.WallServiceGrpc.WallServiceImplBase;
import io.grpc.demo.exchange_o_gram.spanner.WallServiceDb;
import io.grpc.stub.StreamObserver;

public class WallServiceImpl extends WallServiceImplBase {

  private final WallServiceDb db = new WallServiceDb();

  @Override
  public void postToWall(PostToWallRequest request,
      StreamObserver<PostToWallResponse> responseObserver) {
    WallPost post = request.getPost();

    long postId = db.storePost(post);

    // Respond to the gRPC client with the wall post identifier.
    WallPostId wallPostId = WallPostId.newBuilder().setValue(postId).build();
    PostToWallResponse response = PostToWallResponse.newBuilder().setId(wallPostId).build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getWallPosts(GetWallPostsRequest request,
      StreamObserver<GetWallPostsResponse> responseObserver) {

    String username = request.getUsername();

    db.loadPosts(username, (WallPost wallPost) -> {
      GetWallPostsResponse response = GetWallPostsResponse.newBuilder().setPost(wallPost).build();
      // Stream each result immediately to the gRPC client as it is fetched from Spanner.
      responseObserver.onNext(response);
    });

    responseObserver.onCompleted();
  }
}
