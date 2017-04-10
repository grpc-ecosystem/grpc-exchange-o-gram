package io.grpc.demo.exchange_o_gram;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: exchange_o_gram.proto")
public final class MediaServiceGrpc {

  private MediaServiceGrpc() {}

  public static final String SERVICE_NAME = "exchange_o_gram.MediaService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest,
      io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse> METHOD_UPLOAD_IMAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "exchange_o_gram.MediaService", "UploadImage"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest,
      io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse> METHOD_DOWNLOAD_IMAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "exchange_o_gram.MediaService", "DownloadImage"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MediaServiceStub newStub(io.grpc.Channel channel) {
    return new MediaServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MediaServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MediaServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MediaServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MediaServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MediaServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void uploadImage(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPLOAD_IMAGE, responseObserver);
    }

    /**
     */
    public void downloadImage(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DOWNLOAD_IMAGE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_UPLOAD_IMAGE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest,
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse>(
                  this, METHODID_UPLOAD_IMAGE)))
          .addMethod(
            METHOD_DOWNLOAD_IMAGE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest,
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse>(
                  this, METHODID_DOWNLOAD_IMAGE)))
          .build();
    }
  }

  /**
   */
  public static final class MediaServiceStub extends io.grpc.stub.AbstractStub<MediaServiceStub> {
    private MediaServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MediaServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MediaServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MediaServiceStub(channel, callOptions);
    }

    /**
     */
    public void uploadImage(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_IMAGE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void downloadImage(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DOWNLOAD_IMAGE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MediaServiceBlockingStub extends io.grpc.stub.AbstractStub<MediaServiceBlockingStub> {
    private MediaServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MediaServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MediaServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MediaServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse uploadImage(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPLOAD_IMAGE, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse downloadImage(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DOWNLOAD_IMAGE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MediaServiceFutureStub extends io.grpc.stub.AbstractStub<MediaServiceFutureStub> {
    private MediaServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MediaServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MediaServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MediaServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse> uploadImage(
        io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_IMAGE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse> downloadImage(
        io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DOWNLOAD_IMAGE, getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD_IMAGE = 0;
  private static final int METHODID_DOWNLOAD_IMAGE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MediaServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MediaServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_IMAGE:
          serviceImpl.uploadImage((io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.UploadImageResponse>) responseObserver);
          break;
        case METHODID_DOWNLOAD_IMAGE:
          serviceImpl.downloadImage((io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.DownloadImageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class MediaServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.demo.exchange_o_gram.ExchangeOGramProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MediaServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MediaServiceDescriptorSupplier())
              .addMethod(METHOD_UPLOAD_IMAGE)
              .addMethod(METHOD_DOWNLOAD_IMAGE)
              .build();
        }
      }
    }
    return result;
  }
}
