package io.grpc.demo.exchange_o_gram;

import static io.grpc.demo.exchange_o_gram.Utils.DATABASE_ID;
import static io.grpc.demo.exchange_o_gram.Utils.INSTANCE_ID;
import static java.util.Collections.singletonList;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;
import com.google.cloud.spanner.Statement;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.MediaId;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.WallPost;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.WallPostId;
import io.grpc.demo.exchange_o_gram.WallServiceGrpc.WallServiceImplBase;
import io.grpc.stub.StreamObserver;
import java.time.Instant;
import java.util.UUID;

public class WallService extends WallServiceImplBase {

  private final Spanner spanner;
  private final DatabaseId databaseId;

  public WallService() {
    SpannerOptions options = SpannerOptions.newBuilder().build();
    this.spanner = options.getService();
    this.databaseId = DatabaseId.of(options.getProjectId(), INSTANCE_ID, DATABASE_ID);
  }

  @Override
  public void postToWall(PostToWallRequest request,
      StreamObserver<PostToWallResponse> responseObserver) {
    // TODO: validate
    WallPost post = request.getPost();

    DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);

    long postId = UUID.randomUUID().getLeastSignificantBits();
    Mutation.WriteBuilder wallPostBuilder = Mutation.newInsertBuilder("wall_post")
        .set("id")
        .to(postId)
        .set("username")
        .to(post.getUsername())
        .set("caption")
        .to(post.getCaption())
        .set("timestamp_created")
        .to(Instant.now().getEpochSecond());
        
    if (post.hasMediaId())
    {
        System.out.println("mediaId was non-null");
        wallPostBuilder.set("media_id").to(post.getMediaId().getId());
    }
    dbClient.write(singletonList(wallPostBuilder.build()));

    WallPostId response = WallPostId.newBuilder().setId(postId).build();
    responseObserver.onNext(PostToWallResponse.newBuilder().setId(response).build());
    responseObserver.onCompleted();
  }

  @Override
  public void getWallPosts(GetWallPostsRequest request,
      StreamObserver<GetWallPostsResponse> responseObserver) {
    // TODO: validate
    DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);

    Statement stmt =
        Statement.newBuilder("SELECT id, username, caption, media_id, timestamp_created FROM wall_post WHERE username = @username ORDER BY timestamp_created DESC")
            .bind("username").to(request.getUsername())
            .build();

    try(ResultSet resultSet =
        dbClient
            .singleUse()
            .executeQuery(stmt)) {
      
      while (resultSet.next()) {
        WallPost.Builder postBuilder = WallPost.newBuilder();
        long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String caption = resultSet.getString("caption");

        postBuilder.setId(WallPostId.newBuilder().setId(id).build());
        postBuilder.setUsername(username);
        postBuilder.setCaption(caption);
        if (!resultSet.isNull("media_id"))
        {
          postBuilder.setMediaId(MediaId.newBuilder().setId(resultSet.getLong("media_id")).build());
        }
        postBuilder.setTimestampCreated(resultSet.getLong("timestamp_created"));
        responseObserver.onNext(GetWallPostsResponse.newBuilder().setPost(postBuilder.build()).build());
      }
      responseObserver.onCompleted();
    }
  }
}

