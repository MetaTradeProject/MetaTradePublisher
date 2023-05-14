// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fake-trade.proto

package com.freesia.metatradegateway.rpc.proto;

/**
 * Protobuf type {@code rpc.SubmitResult}
 */
public final class SubmitResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:rpc.SubmitResult)
    SubmitResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SubmitResult.newBuilder() to construct.
  private SubmitResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SubmitResult() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SubmitResult();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.freesia.metatradegateway.rpc.proto.FakeTradeProto.internal_static_rpc_SubmitResult_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.freesia.metatradegateway.rpc.proto.FakeTradeProto.internal_static_rpc_SubmitResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.freesia.metatradegateway.rpc.proto.SubmitResult.class, com.freesia.metatradegateway.rpc.proto.SubmitResult.Builder.class);
  }

  public static final int RESULT_FIELD_NUMBER = 1;
  private boolean result_ = false;
  /**
   * <code>bool result = 1;</code>
   * @return The result.
   */
  @java.lang.Override
  public boolean getResult() {
    return result_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (result_ != false) {
      output.writeBool(1, result_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (result_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, result_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.freesia.metatradegateway.rpc.proto.SubmitResult)) {
      return super.equals(obj);
    }
    com.freesia.metatradegateway.rpc.proto.SubmitResult other = (com.freesia.metatradegateway.rpc.proto.SubmitResult) obj;

    if (getResult()
        != other.getResult()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RESULT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getResult());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.freesia.metatradegateway.rpc.proto.SubmitResult parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.freesia.metatradegateway.rpc.proto.SubmitResult prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code rpc.SubmitResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:rpc.SubmitResult)
      com.freesia.metatradegateway.rpc.proto.SubmitResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.freesia.metatradegateway.rpc.proto.FakeTradeProto.internal_static_rpc_SubmitResult_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.freesia.metatradegateway.rpc.proto.FakeTradeProto.internal_static_rpc_SubmitResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.freesia.metatradegateway.rpc.proto.SubmitResult.class, com.freesia.metatradegateway.rpc.proto.SubmitResult.Builder.class);
    }

    // Construct using com.freesia.metatradegateway.rpc.SubmitResult.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      result_ = false;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.freesia.metatradegateway.rpc.proto.FakeTradeProto.internal_static_rpc_SubmitResult_descriptor;
    }

    @java.lang.Override
    public com.freesia.metatradegateway.rpc.proto.SubmitResult getDefaultInstanceForType() {
      return com.freesia.metatradegateway.rpc.proto.SubmitResult.getDefaultInstance();
    }

    @java.lang.Override
    public com.freesia.metatradegateway.rpc.proto.SubmitResult build() {
      com.freesia.metatradegateway.rpc.proto.SubmitResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.freesia.metatradegateway.rpc.proto.SubmitResult buildPartial() {
      com.freesia.metatradegateway.rpc.proto.SubmitResult result = new com.freesia.metatradegateway.rpc.proto.SubmitResult(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.freesia.metatradegateway.rpc.proto.SubmitResult result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.result_ = result_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.freesia.metatradegateway.rpc.proto.SubmitResult) {
        return mergeFrom((com.freesia.metatradegateway.rpc.proto.SubmitResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.freesia.metatradegateway.rpc.proto.SubmitResult other) {
      if (other == com.freesia.metatradegateway.rpc.proto.SubmitResult.getDefaultInstance()) return this;
      if (other.getResult() != false) {
        setResult(other.getResult());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              result_ = input.readBool();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private boolean result_ ;
    /**
     * <code>bool result = 1;</code>
     * @return The result.
     */
    @java.lang.Override
    public boolean getResult() {
      return result_;
    }
    /**
     * <code>bool result = 1;</code>
     * @param value The result to set.
     * @return This builder for chaining.
     */
    public Builder setResult(boolean value) {

      result_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bool result = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearResult() {
      bitField0_ = (bitField0_ & ~0x00000001);
      result_ = false;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:rpc.SubmitResult)
  }

  // @@protoc_insertion_point(class_scope:rpc.SubmitResult)
  private static final com.freesia.metatradegateway.rpc.proto.SubmitResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.freesia.metatradegateway.rpc.proto.SubmitResult();
  }

  public static com.freesia.metatradegateway.rpc.proto.SubmitResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SubmitResult>
      PARSER = new com.google.protobuf.AbstractParser<SubmitResult>() {
    @java.lang.Override
    public SubmitResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<SubmitResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SubmitResult> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.freesia.metatradegateway.rpc.proto.SubmitResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

