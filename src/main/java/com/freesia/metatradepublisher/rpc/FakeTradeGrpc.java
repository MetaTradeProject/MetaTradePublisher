package com.freesia.metatradepublisher.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Interface exported by the server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: fake-trade.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FakeTradeGrpc {

  private FakeTradeGrpc() {}

  public static final String SERVICE_NAME = "rpc.FakeTrade";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage,
      com.freesia.metatradepublisher.rpc.proto.SubmitResult> getSubmitFakeTradeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitFakeTrade",
      requestType = com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage.class,
      responseType = com.freesia.metatradepublisher.rpc.proto.SubmitResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage,
      com.freesia.metatradepublisher.rpc.proto.SubmitResult> getSubmitFakeTradeMethod() {
    io.grpc.MethodDescriptor<com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage, com.freesia.metatradepublisher.rpc.proto.SubmitResult> getSubmitFakeTradeMethod;
    if ((getSubmitFakeTradeMethod = FakeTradeGrpc.getSubmitFakeTradeMethod) == null) {
      synchronized (FakeTradeGrpc.class) {
        if ((getSubmitFakeTradeMethod = FakeTradeGrpc.getSubmitFakeTradeMethod) == null) {
          FakeTradeGrpc.getSubmitFakeTradeMethod = getSubmitFakeTradeMethod =
              io.grpc.MethodDescriptor.<com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage, com.freesia.metatradepublisher.rpc.proto.SubmitResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitFakeTrade"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.freesia.metatradepublisher.rpc.proto.SubmitResult.getDefaultInstance()))
              .setSchemaDescriptor(new FakeTradeMethodDescriptorSupplier("SubmitFakeTrade"))
              .build();
        }
      }
    }
    return getSubmitFakeTradeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FakeTradeStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FakeTradeStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FakeTradeStub>() {
        @java.lang.Override
        public FakeTradeStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FakeTradeStub(channel, callOptions);
        }
      };
    return FakeTradeStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FakeTradeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FakeTradeBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FakeTradeBlockingStub>() {
        @java.lang.Override
        public FakeTradeBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FakeTradeBlockingStub(channel, callOptions);
        }
      };
    return FakeTradeBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FakeTradeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FakeTradeFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FakeTradeFutureStub>() {
        @java.lang.Override
        public FakeTradeFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FakeTradeFutureStub(channel, callOptions);
        }
      };
    return FakeTradeFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void submitFakeTrade(com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage request,
        io.grpc.stub.StreamObserver<com.freesia.metatradepublisher.rpc.proto.SubmitResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitFakeTradeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FakeTrade.
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class FakeTradeImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FakeTradeGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FakeTrade.
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class FakeTradeStub
      extends io.grpc.stub.AbstractAsyncStub<FakeTradeStub> {
    private FakeTradeStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FakeTradeStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FakeTradeStub(channel, callOptions);
    }

    /**
     */
    public void submitFakeTrade(com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage request,
        io.grpc.stub.StreamObserver<com.freesia.metatradepublisher.rpc.proto.SubmitResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitFakeTradeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FakeTrade.
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class FakeTradeBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FakeTradeBlockingStub> {
    private FakeTradeBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FakeTradeBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FakeTradeBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.freesia.metatradepublisher.rpc.proto.SubmitResult submitFakeTrade(com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitFakeTradeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FakeTrade.
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class FakeTradeFutureStub
      extends io.grpc.stub.AbstractFutureStub<FakeTradeFutureStub> {
    private FakeTradeFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FakeTradeFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FakeTradeFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.freesia.metatradepublisher.rpc.proto.SubmitResult> submitFakeTrade(
        com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitFakeTradeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_FAKE_TRADE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBMIT_FAKE_TRADE:
          serviceImpl.submitFakeTrade((com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage) request,
              (io.grpc.stub.StreamObserver<com.freesia.metatradepublisher.rpc.proto.SubmitResult>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubmitFakeTradeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.freesia.metatradepublisher.rpc.proto.FakeTradeMessage,
              com.freesia.metatradepublisher.rpc.proto.SubmitResult>(
                service, METHODID_SUBMIT_FAKE_TRADE)))
        .build();
  }

  private static abstract class FakeTradeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FakeTradeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.freesia.metatradepublisher.rpc.proto.FakeTradeProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FakeTrade");
    }
  }

  private static final class FakeTradeFileDescriptorSupplier
      extends FakeTradeBaseDescriptorSupplier {
    FakeTradeFileDescriptorSupplier() {}
  }

  private static final class FakeTradeMethodDescriptorSupplier
      extends FakeTradeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FakeTradeMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FakeTradeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FakeTradeFileDescriptorSupplier())
              .addMethod(getSubmitFakeTradeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
