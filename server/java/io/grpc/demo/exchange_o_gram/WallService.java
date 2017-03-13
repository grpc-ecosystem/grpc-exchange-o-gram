package io.grpc.demo.exchange_o_gram;

import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse;
import io.grpc.demo.exchange_o_gram.WallServiceGrpc.WallServiceImplBase;
import io.grpc.stub.StreamObserver;

public class WallService extends WallServiceImplBase {

  @Override
  public void postToWall(PostToWallRequest postToWallRequest,
      StreamObserver<PostToWallResponse> streamObserver) {
    super.postToWall(postToWallRequest, streamObserver);
  }

  @Override
  public void getWallPosts(GetWallPostsRequest getWallPostsRequest,
      StreamObserver<GetWallPostsResponse> streamObserver) {
    super.getWallPosts(getWallPostsRequest, streamObserver);
  }
}
