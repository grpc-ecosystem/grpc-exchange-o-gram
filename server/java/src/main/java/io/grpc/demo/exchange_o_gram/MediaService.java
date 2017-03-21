package io.grpc.demo.exchange_o_gram;

import static io.grpc.demo.exchange_o_gram.Utils.*;
import static java.util.Arrays.asList;

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
import java.util.UUID;

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

    Image image = request.getImage();

    DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);

    long imageId = UUID.randomUUID().getLeastSignificantBits();
    Mutation newImage = Mutation.newInsertBuilder("media")
        .set("id")
        .to(imageId)
        .set("data")
        .to(new ByteArray2(image.getData()))
        .set("mimetype")
        .to(image.getMimetype())
        .build();

    dbClient.write(asList(newImage));

    UploadImageResponse response =
        UploadImageResponse.newBuilder().setId(MediaId.newBuilder().setId(imageId)).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void downloadImage(DownloadImageRequest request,
      StreamObserver<DownloadImageResponse> responseObserver) {

    DatabaseClient dbClient = spanner.getDatabaseClient(databaseId);
    Key mediaId = Key.of(request.getId().getId());

    Struct row =
        dbClient.singleUse().readRow("media", mediaId, asList("id", "data", "mimetype"));
    if (row == null) {
      throw new NoSuchElementException("Image with id '" + mediaId + "' was not found.");
    }

    try {
      ByteString data =
          ByteString.readFrom(row.getBytes("data").asInputStream());
      String filetype = row.getString("mimetype");

      DownloadImageResponse response =
          DownloadImageResponse.newBuilder()
              .setImage(Image.newBuilder().setData(data).setMimetype(filetype).build())
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (IOException e) {
      responseObserver.onError(e);
    }
  }

  // I ll open a PR on Google Cloud Spanner libraries so that this is no longer necessary.
  private static class ByteArray2 extends ByteArray {

    ByteArray2(ByteString byteString) {
      super(byteString);
    }
  }
}
