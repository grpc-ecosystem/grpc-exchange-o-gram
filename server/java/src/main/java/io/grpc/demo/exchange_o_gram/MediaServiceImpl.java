package io.grpc.demo.exchange_o_gram;

import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.Image;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.MediaId;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse;
import io.grpc.demo.exchange_o_gram.MediaServiceGrpc.MediaServiceImplBase;
import io.grpc.demo.exchange_o_gram.spanner.MediaServiceDb;
import io.grpc.stub.StreamObserver;

public class MediaServiceImpl extends MediaServiceImplBase {

  private final MediaServiceDb db = new MediaServiceDb();

  @Override
  public void uploadImage(UploadImageRequest request,
      StreamObserver<UploadImageResponse> responseObserver) {

    // The image to be uploaded.
    Image image = request.getImage();

    long id = db.storeImage(image);

    // Send a response to the gRPC client.
    MediaId mediaId = MediaId.newBuilder().setValue(id).build();
    UploadImageResponse response = UploadImageResponse.newBuilder().setId(mediaId).build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void downloadImage(DownloadImageRequest request,
      StreamObserver<DownloadImageResponse> responseObserver) {

    Image image = db.loadImage(request.getId().getValue());

    DownloadImageResponse response = DownloadImageResponse.newBuilder().setImage(image).build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
