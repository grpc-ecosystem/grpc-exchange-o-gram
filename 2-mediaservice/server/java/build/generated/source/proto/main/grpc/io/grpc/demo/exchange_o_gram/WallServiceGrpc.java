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
public final class WallServiceGrpc {

  private WallServiceGrpc() {}

  public static final String SERVICE_NAME = "exchange_o_gram.WallService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest,
      io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse> METHOD_POST_TO_WALL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "exchange_o_gram.WallService", "PostToWall"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest,
      io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse> METHOD_GET_WALL_POSTS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "exchange_o_gram.WallService", "GetWallPosts"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WallServiceStub newStub(io.grpc.Channel channel) {
    return new WallServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WallServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WallServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static WallServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WallServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class WallServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void postToWall(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_POST_TO_WALL, responseObserver);
    }

    /**
     */
    public void getWallPosts(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_WALL_POSTS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_POST_TO_WALL,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest,
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse>(
                  this, METHODID_POST_TO_WALL)))
          .addMethod(
            METHOD_GET_WALL_POSTS,
            asyncServerStreamingCall(
              new MethodHandlers<
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest,
                io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse>(
                  this, METHODID_GET_WALL_POSTS)))
          .build();
    }
  }

  /**
   */
  public static final class WallServiceStub extends io.grpc.stub.AbstractStub<WallServiceStub> {
    private WallServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WallServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WallServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WallServiceStub(channel, callOptions);
    }

    /**
     */
    public void postToWall(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_POST_TO_WALL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getWallPosts(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest request,
        io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_WALL_POSTS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WallServiceBlockingStub extends io.grpc.stub.AbstractStub<WallServiceBlockingStub> {
    private WallServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WallServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WallServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WallServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse postToWall(io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_POST_TO_WALL, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse> getWallPosts(
        io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_WALL_POSTS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WallServiceFutureStub extends io.grpc.stub.AbstractStub<WallServiceFutureStub> {
    private WallServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WallServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WallServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WallServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse> postToWall(
        io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_POST_TO_WALL, getCallOptions()), request);
    }
  }

  private static final int METHODID_POST_TO_WALL = 0;
  private static final int METHODID_GET_WALL_POSTS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WallServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WallServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_POST_TO_WALL:
          serviceImpl.postToWall((io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.PostToWallResponse>) responseObserver);
          break;
        case METHODID_GET_WALL_POSTS:
          serviceImpl.getWallPosts((io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.demo.exchange_o_gram.ExchangeOGramProto.GetWallPostsResponse>) responseObserver);
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

  private static final class WallServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.demo.exchange_o_gram.ExchangeOGramProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WallServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WallServiceDescriptorSupplier())
              .addMethod(METHOD_POST_TO_WALL)
              .addMethod(METHOD_GET_WALL_POSTS)
              .build();
        }
      }
    }
    return result;
  }
}
