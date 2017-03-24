package io.grpc.demo.exchange_o_gram;

import static io.grpc.demo.exchange_o_gram.Utils.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import com.google.cloud.ByteArray;
import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;
import com.google.cloud.spanner.Struct;
import com.google.protobuf.ByteString;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.Image;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.MediaId;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse;
import io.grpc.demo.exchange_o_gram.MediaServiceGrpc.MediaServiceImplBase;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.NoSuchElementException;

public class MediaService extends MediaServiceImplBase {

  private final Spanner spanner;
  private final DatabaseId databaseId;

  public MediaService() {
    SpannerOptions options = SpannerOptions.newBuilder().build();
    this.spanner = options.getService();
    this.databaseId = DatabaseId.of(options.getProjectId(), INSTANCE_ID, DATABASE_ID);
  }

  @Override
  public void uploadImage(UploadImageRequest request,
      StreamObserver<UploadImageResponse> responseObserver) {

    // The image to be uploaded.
    Image image = request.getImage();

    long id = storeInSpanner(image);

    // Send a response to the gRPC client.
    MediaId mediaId = MediaId.newBuilder().setId(id).build();
    UploadImageResponse response = UploadImageResponse.newBuilder().setId(mediaId).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void downloadImage(DownloadImageRequest request,
      StreamObserver<DownloadImageResponse> responseObserver) {

    Image image = loadFromSpanner(request.getId().getId());

    DownloadImageResponse response = DownloadImageResponse.newBuilder().setImage(image).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private Image loadFromSpanner(long id) {
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
      Image image = Image.newBuilder().setData(data).setMimetype(filetype).build();
      return image;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private long storeInSpanner(Image image) {
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
