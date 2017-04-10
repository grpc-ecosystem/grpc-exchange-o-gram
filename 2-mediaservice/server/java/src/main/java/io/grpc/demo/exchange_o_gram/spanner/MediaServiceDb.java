package io.grpc.demo.exchange_o_gram.spanner;

import com.google.cloud.ByteArray;
import com.google.cloud.spanner.*;
import com.google.protobuf.ByteString;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto;

import java.io.IOException;
import java.util.NoSuchElementException;

import static io.grpc.demo.exchange_o_gram.Utils.DATABASE_ID;
import static io.grpc.demo.exchange_o_gram.Utils.INSTANCE_ID;
import static io.grpc.demo.exchange_o_gram.Utils.newId;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class MediaServiceDb {

    private final Spanner spanner;
    private final DatabaseId databaseId;

    public MediaServiceDb() {
        SpannerOptions options = SpannerOptions.newBuilder().build();
        this.spanner = options.getService();
        this.databaseId = DatabaseId.of(options.getProjectId(), INSTANCE_ID, DATABASE_ID);
    }

    public ExchangeOGramProto.Image loadImage(long id) {
        // Fetch the image from Spanner.
        DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);
        Key mediaId = Key.of(id);
        Struct row = dbClient.singleUse().readRow("media", mediaId, asList("id", "data", "mimetype"));

        if (row == null) {
            throw new NoSuchElementException("Image with id '" + mediaId + "' was not found.");
        }

        try {
            // Send the image to the gRPC client.
            ByteString data = ByteString.readFrom(row.getBytes("data").asInputStream());
            String filetype = row.getString("mimetype");
            ExchangeOGramProto.Image image = ExchangeOGramProto.Image.newBuilder().setData(data).setMimetype(filetype).build();
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long storeImage(ExchangeOGramProto.Image image) {
        long id = newId();
        Mutation newImage = Mutation
                .newInsertBuilder("media")
                .set("id")
                .to(id)
                .set("data")
                .to(toByteArray(image.getData()))
                .set("mimetype")
                .to(image.getMimetype())
                .build();

        DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);
        dbClient.write(singletonList(newImage));

        return id;
    }

    private static ByteArray toByteArray(ByteString bs) {
        // This copy could be avoided: https://github.com/GoogleCloudPlatform/google-cloud-java/issues/1763
        return ByteArray.copyFrom(bs.asReadOnlyByteBuffer());
    }
}
