package io.grpc.demo.exchange_o_gram;

import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest;
import io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse;
import io.grpc.demo.exchange_o_gram.MediaServiceGrpc.MediaServiceImplBase;
import io.grpc.stub.StreamObserver;

public class MediaService extends MediaServiceImplBase {

  @Override
  public void uploadImage(UploadImageRequest uploadImageRequest,
      StreamObserver<UploadImageResponse> streamObserver) {
    super.uploadImage(uploadImageRequest, streamObserver);
  }

  @Override
  public void downloadImage(DownloadImageRequest downloadImageRequest,
      StreamObserver<DownloadImageResponse> streamObserver) {
    super.downloadImage(downloadImageRequest, streamObserver);
  }
}
