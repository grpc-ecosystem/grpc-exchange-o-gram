package io.grpc.demo.exchange_o_gram.spanner;

import com.google.cloud.spanner.*;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto;

import java.time.Instant;
import java.util.function.Consumer;

import static io.grpc.demo.exchange_o_gram.Utils.DATABASE_ID;
import static io.grpc.demo.exchange_o_gram.Utils.INSTANCE_ID;
import static io.grpc.demo.exchange_o_gram.Utils.newId;
import static java.util.Collections.singletonList;

public class WallServiceDb {
    private final Spanner spanner;
    private final DatabaseId databaseId;

    public WallServiceDb() {
        SpannerOptions options = SpannerOptions.newBuilder().build();
        this.spanner = options.getService();
        this.databaseId = DatabaseId.of(options.getProjectId(), INSTANCE_ID, DATABASE_ID);
    }

    public void loadPosts(String username, Consumer<ExchangeOGramProto.WallPost> wallPostConsumer) {
        DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);

        // Fetch all posts made by username from Spanner.
        Statement stmt = Statement.newBuilder(
                "SELECT id, username, caption, media_id, timestamp_created "
                        + "FROM wall_post "
                        + "WHERE username = @username "
                        + "ORDER BY timestamp_created DESC")
                .bind("username").to(username)
                .build();

        try(ResultSet resultSet = dbClient.singleUse().executeQuery(stmt)) {
            while (resultSet.next()) {
                ExchangeOGramProto.WallPost.Builder postBuilder = ExchangeOGramProto.WallPost.newBuilder();
                long id = resultSet.getLong("id");
                String caption = resultSet.getString("caption");
                long timestamp = resultSet.getLong("timestamp_created");

                postBuilder.setId(ExchangeOGramProto.WallPostId.newBuilder().setValue(id).build());
                postBuilder.setUsername(username);
                postBuilder.setCaption(caption);
                postBuilder.setTimestampCreated(timestamp);

                // The media is optional.
                if (!resultSet.isNull("media_id")) {
                    ExchangeOGramProto.MediaId mediaId = ExchangeOGramProto.MediaId.newBuilder().setValue(resultSet.getLong("media_id")).build();
                    postBuilder.setMediaId(mediaId);
                }

                ExchangeOGramProto.WallPost wallPost = postBuilder.build();
                wallPostConsumer.accept(wallPost);
            }
        }
    }

    public long storePost(ExchangeOGramProto.WallPost post) {
        long postId = newId();
        Mutation.WriteBuilder wallPostBuilder = Mutation
                .newInsertBuilder("wall_post")
                .set("id")
                .to(postId)
                .set("username")
                .to(post.getUsername())
                .set("caption")
                .to(post.getCaption())
                .set("timestamp_created")
                .to(Instant.now().getEpochSecond());

        // The image is optional.
        if (post.hasMediaId()) {
            wallPostBuilder.set("media_id").to(post.getMediaId().getValue());
        }

        Mutation wallPost = wallPostBuilder.build();

        DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);
        dbClient.write(singletonList(wallPost));
        return postId;
    }
}
