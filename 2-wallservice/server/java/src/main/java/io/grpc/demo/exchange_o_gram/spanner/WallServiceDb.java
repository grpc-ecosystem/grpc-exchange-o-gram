package io.grpc.demo.exchange_o_gram.spanner;

import com.google.cloud.spanner.*;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto;

import java.time.Instant;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

    public Iterable<ExchangeOGramProto.WallPost> loadPosts(String username) {
        class LoadPostsIter implements Iterator<ExchangeOGramProto.WallPost> {

            private boolean shouldFetchNext;
            private ExchangeOGramProto.WallPost next;

            private ResultSet resultSet;

            private LoadPostsIter() {
                DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);
                Statement stmt = Statement.newBuilder(
                        "SELECT id, username, caption, media_id, timestamp_created "
                                + "FROM wall_post "
                                + "WHERE username = @username "
                                + "ORDER BY timestamp_created DESC")
                        .bind("username").to(username)
                        .build();
                resultSet = dbClient.singleUse().executeQuery(stmt);
                shouldFetchNext = true;
            }

            private ExchangeOGramProto.WallPost fetchNext() {
                if (!resultSet.next()) {
                    return null;
                }

                ExchangeOGramProto.WallPost.Builder postBuilder = ExchangeOGramProto.WallPost.newBuilder();
                long id = resultSet.getLong("id");
                String caption = resultSet.getString("caption");
                long timestamp = resultSet.getLong("timestamp_created");

                postBuilder.setId(ExchangeOGramProto.WallPostId.newBuilder().setValue(id).build());
                postBuilder.setUsername(username);
                postBuilder.setCaption(caption);
                postBuilder.setTimestampCreated(timestamp);

                return postBuilder.build();
            }

            @Override
            public boolean hasNext() {
                if (shouldFetchNext) {
                    next = fetchNext();
                }

                shouldFetchNext = false;

                return next != null;
            }

            @Override
            public ExchangeOGramProto.WallPost next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more wall posts");
                }

                shouldFetchNext = true;

                return next;
            }
        }

        return () -> new LoadPostsIter();
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
        
        Mutation wallPost = wallPostBuilder.build();

        DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);
        dbClient.write(singletonList(wallPost));
        return postId;
    }
}
