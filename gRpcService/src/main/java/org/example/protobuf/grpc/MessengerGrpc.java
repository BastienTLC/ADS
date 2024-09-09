package org.example.protobuf.grpc;

import org.example.protobuf.java.MessengerOuterClass;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.46.0)",
    comments = "Source: Messenger.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MessengerGrpc {

  private MessengerGrpc() {}

  public static final String SERVICE_NAME = "Messenger";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Message,
      MessengerOuterClass.Empty> getStoreMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StoreMessage",
      requestType = MessengerOuterClass.Message.class,
      responseType = MessengerOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Message,
      MessengerOuterClass.Empty> getStoreMessageMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Message, MessengerOuterClass.Empty> getStoreMessageMethod;
    if ((getStoreMessageMethod = MessengerGrpc.getStoreMessageMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getStoreMessageMethod = MessengerGrpc.getStoreMessageMethod) == null) {
          MessengerGrpc.getStoreMessageMethod = getStoreMessageMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Message, MessengerOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StoreMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("StoreMessage"))
              .build();
        }
      }
    }
    return getStoreMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.MessageBatch,
      MessengerOuterClass.Empty> getStoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Store",
      requestType = MessengerOuterClass.MessageBatch.class,
      responseType = MessengerOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.MessageBatch,
      MessengerOuterClass.Empty> getStoreMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.MessageBatch, MessengerOuterClass.Empty> getStoreMethod;
    if ((getStoreMethod = MessengerGrpc.getStoreMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getStoreMethod = MessengerGrpc.getStoreMethod) == null) {
          MessengerGrpc.getStoreMethod = getStoreMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.MessageBatch, MessengerOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Store"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("Store"))
              .build();
        }
      }
    }
    return getStoreMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.MessageId,
      MessengerOuterClass.Message> getRetrieveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RetrieveMessage",
      requestType = MessengerOuterClass.MessageId.class,
      responseType = MessengerOuterClass.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.MessageId,
      MessengerOuterClass.Message> getRetrieveMessageMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.MessageId, MessengerOuterClass.Message> getRetrieveMessageMethod;
    if ((getRetrieveMessageMethod = MessengerGrpc.getRetrieveMessageMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getRetrieveMessageMethod = MessengerGrpc.getRetrieveMessageMethod) == null) {
          MessengerGrpc.getRetrieveMessageMethod = getRetrieveMessageMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.MessageId, MessengerOuterClass.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RetrieveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Message.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("RetrieveMessage"))
              .build();
        }
      }
    }
    return getRetrieveMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.MessageIdBatch,
      MessengerOuterClass.MessageBatch> getRetrieveMessagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RetrieveMessages",
      requestType = MessengerOuterClass.MessageIdBatch.class,
      responseType = MessengerOuterClass.MessageBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.MessageIdBatch,
      MessengerOuterClass.MessageBatch> getRetrieveMessagesMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.MessageIdBatch, MessengerOuterClass.MessageBatch> getRetrieveMessagesMethod;
    if ((getRetrieveMessagesMethod = MessengerGrpc.getRetrieveMessagesMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getRetrieveMessagesMethod = MessengerGrpc.getRetrieveMessagesMethod) == null) {
          MessengerGrpc.getRetrieveMessagesMethod = getRetrieveMessagesMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.MessageIdBatch, MessengerOuterClass.MessageBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RetrieveMessages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageIdBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("RetrieveMessages"))
              .build();
        }
      }
    }
    return getRetrieveMessagesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.MessageId,
      MessengerOuterClass.Empty> getDeleteMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteMessage",
      requestType = MessengerOuterClass.MessageId.class,
      responseType = MessengerOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.MessageId,
      MessengerOuterClass.Empty> getDeleteMessageMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.MessageId, MessengerOuterClass.Empty> getDeleteMessageMethod;
    if ((getDeleteMessageMethod = MessengerGrpc.getDeleteMessageMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getDeleteMessageMethod = MessengerGrpc.getDeleteMessageMethod) == null) {
          MessengerGrpc.getDeleteMessageMethod = getDeleteMessageMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.MessageId, MessengerOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("DeleteMessage"))
              .build();
        }
      }
    }
    return getDeleteMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.MessageIdBatch,
      MessengerOuterClass.Empty> getDeleteMessagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteMessages",
      requestType = MessengerOuterClass.MessageIdBatch.class,
      responseType = MessengerOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.MessageIdBatch,
      MessengerOuterClass.Empty> getDeleteMessagesMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.MessageIdBatch, MessengerOuterClass.Empty> getDeleteMessagesMethod;
    if ((getDeleteMessagesMethod = MessengerGrpc.getDeleteMessagesMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getDeleteMessagesMethod = MessengerGrpc.getDeleteMessagesMethod) == null) {
          MessengerGrpc.getDeleteMessagesMethod = getDeleteMessagesMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.MessageIdBatch, MessengerOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteMessages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageIdBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("DeleteMessages"))
              .build();
        }
      }
    }
    return getDeleteMessagesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.SubscribeRequest,
      MessengerOuterClass.TopicBatch> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subscribe",
      requestType = MessengerOuterClass.SubscribeRequest.class,
      responseType = MessengerOuterClass.TopicBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.SubscribeRequest,
      MessengerOuterClass.TopicBatch> getSubscribeMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.SubscribeRequest, MessengerOuterClass.TopicBatch> getSubscribeMethod;
    if ((getSubscribeMethod = MessengerGrpc.getSubscribeMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getSubscribeMethod = MessengerGrpc.getSubscribeMethod) == null) {
          MessengerGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.SubscribeRequest, MessengerOuterClass.TopicBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.TopicBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("Subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.SubscribeRequest,
      MessengerOuterClass.TopicBatch> getUnsubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unsubscribe",
      requestType = MessengerOuterClass.SubscribeRequest.class,
      responseType = MessengerOuterClass.TopicBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.SubscribeRequest,
      MessengerOuterClass.TopicBatch> getUnsubscribeMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.SubscribeRequest, MessengerOuterClass.TopicBatch> getUnsubscribeMethod;
    if ((getUnsubscribeMethod = MessengerGrpc.getUnsubscribeMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getUnsubscribeMethod = MessengerGrpc.getUnsubscribeMethod) == null) {
          MessengerGrpc.getUnsubscribeMethod = getUnsubscribeMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.SubscribeRequest, MessengerOuterClass.TopicBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unsubscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.TopicBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("Unsubscribe"))
              .build();
        }
      }
    }
    return getUnsubscribeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Empty,
      MessengerOuterClass.UsernameBatch> getListUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListUser",
      requestType = MessengerOuterClass.Empty.class,
      responseType = MessengerOuterClass.UsernameBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Empty,
      MessengerOuterClass.UsernameBatch> getListUserMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Empty, MessengerOuterClass.UsernameBatch> getListUserMethod;
    if ((getListUserMethod = MessengerGrpc.getListUserMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getListUserMethod = MessengerGrpc.getListUserMethod) == null) {
          MessengerGrpc.getListUserMethod = getListUserMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Empty, MessengerOuterClass.UsernameBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.UsernameBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("ListUser"))
              .build();
        }
      }
    }
    return getListUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Empty,
      MessengerOuterClass.TopicBatch> getListTopicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListTopic",
      requestType = MessengerOuterClass.Empty.class,
      responseType = MessengerOuterClass.TopicBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Empty,
      MessengerOuterClass.TopicBatch> getListTopicMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Empty, MessengerOuterClass.TopicBatch> getListTopicMethod;
    if ((getListTopicMethod = MessengerGrpc.getListTopicMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getListTopicMethod = MessengerGrpc.getListTopicMethod) == null) {
          MessengerGrpc.getListTopicMethod = getListTopicMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Empty, MessengerOuterClass.TopicBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListTopic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.TopicBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("ListTopic"))
              .build();
        }
      }
    }
    return getListTopicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Username,
      MessengerOuterClass.TopicBatch> getListTopicByUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListTopicByUser",
      requestType = MessengerOuterClass.Username.class,
      responseType = MessengerOuterClass.TopicBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Username,
      MessengerOuterClass.TopicBatch> getListTopicByUserMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Username, MessengerOuterClass.TopicBatch> getListTopicByUserMethod;
    if ((getListTopicByUserMethod = MessengerGrpc.getListTopicByUserMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getListTopicByUserMethod = MessengerGrpc.getListTopicByUserMethod) == null) {
          MessengerGrpc.getListTopicByUserMethod = getListTopicByUserMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Username, MessengerOuterClass.TopicBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListTopicByUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.TopicBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("ListTopicByUser"))
              .build();
        }
      }
    }
    return getListTopicByUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Topic,
      MessengerOuterClass.UsernameBatch> getListSubscriberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListSubscriber",
      requestType = MessengerOuterClass.Topic.class,
      responseType = MessengerOuterClass.UsernameBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Topic,
      MessengerOuterClass.UsernameBatch> getListSubscriberMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Topic, MessengerOuterClass.UsernameBatch> getListSubscriberMethod;
    if ((getListSubscriberMethod = MessengerGrpc.getListSubscriberMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getListSubscriberMethod = MessengerGrpc.getListSubscriberMethod) == null) {
          MessengerGrpc.getListSubscriberMethod = getListSubscriberMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Topic, MessengerOuterClass.UsernameBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListSubscriber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Topic.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.UsernameBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("ListSubscriber"))
              .build();
        }
      }
    }
    return getListSubscriberMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Username,
      MessengerOuterClass.MessageIdBatch> getListMessageByUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMessageByUser",
      requestType = MessengerOuterClass.Username.class,
      responseType = MessengerOuterClass.MessageIdBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Username,
      MessengerOuterClass.MessageIdBatch> getListMessageByUserMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Username, MessengerOuterClass.MessageIdBatch> getListMessageByUserMethod;
    if ((getListMessageByUserMethod = MessengerGrpc.getListMessageByUserMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getListMessageByUserMethod = MessengerGrpc.getListMessageByUserMethod) == null) {
          MessengerGrpc.getListMessageByUserMethod = getListMessageByUserMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Username, MessengerOuterClass.MessageIdBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMessageByUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageIdBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("ListMessageByUser"))
              .build();
        }
      }
    }
    return getListMessageByUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MessengerOuterClass.Topic,
      MessengerOuterClass.MessageIdBatch> getListMessageByTopicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMessageByTopic",
      requestType = MessengerOuterClass.Topic.class,
      responseType = MessengerOuterClass.MessageIdBatch.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MessengerOuterClass.Topic,
      MessengerOuterClass.MessageIdBatch> getListMessageByTopicMethod() {
    io.grpc.MethodDescriptor<MessengerOuterClass.Topic, MessengerOuterClass.MessageIdBatch> getListMessageByTopicMethod;
    if ((getListMessageByTopicMethod = MessengerGrpc.getListMessageByTopicMethod) == null) {
      synchronized (MessengerGrpc.class) {
        if ((getListMessageByTopicMethod = MessengerGrpc.getListMessageByTopicMethod) == null) {
          MessengerGrpc.getListMessageByTopicMethod = getListMessageByTopicMethod =
              io.grpc.MethodDescriptor.<MessengerOuterClass.Topic, MessengerOuterClass.MessageIdBatch>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMessageByTopic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.Topic.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MessengerOuterClass.MessageIdBatch.getDefaultInstance()))
              .setSchemaDescriptor(new MessengerMethodDescriptorSupplier("ListMessageByTopic"))
              .build();
        }
      }
    }
    return getListMessageByTopicMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessengerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessengerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessengerStub>() {
        @java.lang.Override
        public MessengerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessengerStub(channel, callOptions);
        }
      };
    return MessengerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessengerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessengerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessengerBlockingStub>() {
        @java.lang.Override
        public MessengerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessengerBlockingStub(channel, callOptions);
        }
      };
    return MessengerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessengerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessengerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessengerFutureStub>() {
        @java.lang.Override
        public MessengerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessengerFutureStub(channel, callOptions);
        }
      };
    return MessengerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MessengerImplBase implements io.grpc.BindableService {

    /**
     */
    public void storeMessage(MessengerOuterClass.Message request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStoreMessageMethod(), responseObserver);
    }

    /**
     */
    public void store(MessengerOuterClass.MessageBatch request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStoreMethod(), responseObserver);
    }

    /**
     */
    public void retrieveMessage(MessengerOuterClass.MessageId request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRetrieveMessageMethod(), responseObserver);
    }

    /**
     */
    public void retrieveMessages(MessengerOuterClass.MessageIdBatch request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.MessageBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRetrieveMessagesMethod(), responseObserver);
    }

    /**
     */
    public void deleteMessage(MessengerOuterClass.MessageId request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMessageMethod(), responseObserver);
    }

    /**
     */
    public void deleteMessages(MessengerOuterClass.MessageIdBatch request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMessagesMethod(), responseObserver);
    }

    /**
     */
    public void subscribe(MessengerOuterClass.SubscribeRequest request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    /**
     */
    public void unsubscribe(MessengerOuterClass.SubscribeRequest request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnsubscribeMethod(), responseObserver);
    }

    /**
     */
    public void listUser(MessengerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.UsernameBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListUserMethod(), responseObserver);
    }

    /**
     */
    public void listTopic(MessengerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTopicMethod(), responseObserver);
    }

    /**
     */
    public void listTopicByUser(MessengerOuterClass.Username request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTopicByUserMethod(), responseObserver);
    }

    /**
     */
    public void listSubscriber(MessengerOuterClass.Topic request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.UsernameBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListSubscriberMethod(), responseObserver);
    }

    /**
     */
    public void listMessageByUser(MessengerOuterClass.Username request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.MessageIdBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMessageByUserMethod(), responseObserver);
    }

    /**
     */
    public void listMessageByTopic(MessengerOuterClass.Topic request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.MessageIdBatch> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMessageByTopicMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStoreMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Message,
                MessengerOuterClass.Empty>(
                  this, METHODID_STORE_MESSAGE)))
          .addMethod(
            getStoreMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.MessageBatch,
                MessengerOuterClass.Empty>(
                  this, METHODID_STORE)))
          .addMethod(
            getRetrieveMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.MessageId,
                MessengerOuterClass.Message>(
                  this, METHODID_RETRIEVE_MESSAGE)))
          .addMethod(
            getRetrieveMessagesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.MessageIdBatch,
                MessengerOuterClass.MessageBatch>(
                  this, METHODID_RETRIEVE_MESSAGES)))
          .addMethod(
            getDeleteMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.MessageId,
                MessengerOuterClass.Empty>(
                  this, METHODID_DELETE_MESSAGE)))
          .addMethod(
            getDeleteMessagesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.MessageIdBatch,
                MessengerOuterClass.Empty>(
                  this, METHODID_DELETE_MESSAGES)))
          .addMethod(
            getSubscribeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.SubscribeRequest,
                MessengerOuterClass.TopicBatch>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getUnsubscribeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.SubscribeRequest,
                MessengerOuterClass.TopicBatch>(
                  this, METHODID_UNSUBSCRIBE)))
          .addMethod(
            getListUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Empty,
                MessengerOuterClass.UsernameBatch>(
                  this, METHODID_LIST_USER)))
          .addMethod(
            getListTopicMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Empty,
                MessengerOuterClass.TopicBatch>(
                  this, METHODID_LIST_TOPIC)))
          .addMethod(
            getListTopicByUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Username,
                MessengerOuterClass.TopicBatch>(
                  this, METHODID_LIST_TOPIC_BY_USER)))
          .addMethod(
            getListSubscriberMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Topic,
                MessengerOuterClass.UsernameBatch>(
                  this, METHODID_LIST_SUBSCRIBER)))
          .addMethod(
            getListMessageByUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Username,
                MessengerOuterClass.MessageIdBatch>(
                  this, METHODID_LIST_MESSAGE_BY_USER)))
          .addMethod(
            getListMessageByTopicMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MessengerOuterClass.Topic,
                MessengerOuterClass.MessageIdBatch>(
                  this, METHODID_LIST_MESSAGE_BY_TOPIC)))
          .build();
    }
  }

  /**
   */
  public static final class MessengerStub extends io.grpc.stub.AbstractAsyncStub<MessengerStub> {
    private MessengerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessengerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessengerStub(channel, callOptions);
    }

    /**
     */
    public void storeMessage(MessengerOuterClass.Message request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStoreMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void store(MessengerOuterClass.MessageBatch request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void retrieveMessage(MessengerOuterClass.MessageId request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRetrieveMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void retrieveMessages(MessengerOuterClass.MessageIdBatch request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.MessageBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRetrieveMessagesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteMessage(MessengerOuterClass.MessageId request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteMessages(MessengerOuterClass.MessageIdBatch request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMessagesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribe(MessengerOuterClass.SubscribeRequest request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unsubscribe(MessengerOuterClass.SubscribeRequest request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnsubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listUser(MessengerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.UsernameBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listTopic(MessengerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListTopicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listTopicByUser(MessengerOuterClass.Username request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListTopicByUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listSubscriber(MessengerOuterClass.Topic request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.UsernameBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListSubscriberMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listMessageByUser(MessengerOuterClass.Username request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.MessageIdBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMessageByUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listMessageByTopic(MessengerOuterClass.Topic request,
        io.grpc.stub.StreamObserver<MessengerOuterClass.MessageIdBatch> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMessageByTopicMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MessengerBlockingStub extends io.grpc.stub.AbstractBlockingStub<MessengerBlockingStub> {
    private MessengerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessengerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessengerBlockingStub(channel, callOptions);
    }

    /**
     */
    public MessengerOuterClass.Empty storeMessage(MessengerOuterClass.Message request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStoreMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.Empty store(MessengerOuterClass.MessageBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStoreMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.Message retrieveMessage(MessengerOuterClass.MessageId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRetrieveMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.MessageBatch retrieveMessages(MessengerOuterClass.MessageIdBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRetrieveMessagesMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.Empty deleteMessage(MessengerOuterClass.MessageId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.Empty deleteMessages(MessengerOuterClass.MessageIdBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMessagesMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.TopicBatch subscribe(MessengerOuterClass.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.TopicBatch unsubscribe(MessengerOuterClass.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnsubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.UsernameBatch listUser(MessengerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.TopicBatch listTopic(MessengerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListTopicMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.TopicBatch listTopicByUser(MessengerOuterClass.Username request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListTopicByUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.UsernameBatch listSubscriber(MessengerOuterClass.Topic request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListSubscriberMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.MessageIdBatch listMessageByUser(MessengerOuterClass.Username request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMessageByUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public MessengerOuterClass.MessageIdBatch listMessageByTopic(MessengerOuterClass.Topic request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMessageByTopicMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessengerFutureStub extends io.grpc.stub.AbstractFutureStub<MessengerFutureStub> {
    private MessengerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessengerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessengerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.Empty> storeMessage(
        MessengerOuterClass.Message request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStoreMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.Empty> store(
        MessengerOuterClass.MessageBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.Message> retrieveMessage(
        MessengerOuterClass.MessageId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRetrieveMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.MessageBatch> retrieveMessages(
        MessengerOuterClass.MessageIdBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRetrieveMessagesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.Empty> deleteMessage(
        MessengerOuterClass.MessageId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.Empty> deleteMessages(
        MessengerOuterClass.MessageIdBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMessagesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.TopicBatch> subscribe(
        MessengerOuterClass.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.TopicBatch> unsubscribe(
        MessengerOuterClass.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnsubscribeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.UsernameBatch> listUser(
        MessengerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.TopicBatch> listTopic(
        MessengerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListTopicMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.TopicBatch> listTopicByUser(
        MessengerOuterClass.Username request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListTopicByUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.UsernameBatch> listSubscriber(
        MessengerOuterClass.Topic request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListSubscriberMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.MessageIdBatch> listMessageByUser(
        MessengerOuterClass.Username request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMessageByUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MessengerOuterClass.MessageIdBatch> listMessageByTopic(
        MessengerOuterClass.Topic request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMessageByTopicMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STORE_MESSAGE = 0;
  private static final int METHODID_STORE = 1;
  private static final int METHODID_RETRIEVE_MESSAGE = 2;
  private static final int METHODID_RETRIEVE_MESSAGES = 3;
  private static final int METHODID_DELETE_MESSAGE = 4;
  private static final int METHODID_DELETE_MESSAGES = 5;
  private static final int METHODID_SUBSCRIBE = 6;
  private static final int METHODID_UNSUBSCRIBE = 7;
  private static final int METHODID_LIST_USER = 8;
  private static final int METHODID_LIST_TOPIC = 9;
  private static final int METHODID_LIST_TOPIC_BY_USER = 10;
  private static final int METHODID_LIST_SUBSCRIBER = 11;
  private static final int METHODID_LIST_MESSAGE_BY_USER = 12;
  private static final int METHODID_LIST_MESSAGE_BY_TOPIC = 13;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessengerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessengerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STORE_MESSAGE:
          serviceImpl.storeMessage((MessengerOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.Empty>) responseObserver);
          break;
        case METHODID_STORE:
          serviceImpl.store((MessengerOuterClass.MessageBatch) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.Empty>) responseObserver);
          break;
        case METHODID_RETRIEVE_MESSAGE:
          serviceImpl.retrieveMessage((MessengerOuterClass.MessageId) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.Message>) responseObserver);
          break;
        case METHODID_RETRIEVE_MESSAGES:
          serviceImpl.retrieveMessages((MessengerOuterClass.MessageIdBatch) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.MessageBatch>) responseObserver);
          break;
        case METHODID_DELETE_MESSAGE:
          serviceImpl.deleteMessage((MessengerOuterClass.MessageId) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.Empty>) responseObserver);
          break;
        case METHODID_DELETE_MESSAGES:
          serviceImpl.deleteMessages((MessengerOuterClass.MessageIdBatch) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.Empty>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((MessengerOuterClass.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch>) responseObserver);
          break;
        case METHODID_UNSUBSCRIBE:
          serviceImpl.unsubscribe((MessengerOuterClass.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch>) responseObserver);
          break;
        case METHODID_LIST_USER:
          serviceImpl.listUser((MessengerOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.UsernameBatch>) responseObserver);
          break;
        case METHODID_LIST_TOPIC:
          serviceImpl.listTopic((MessengerOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch>) responseObserver);
          break;
        case METHODID_LIST_TOPIC_BY_USER:
          serviceImpl.listTopicByUser((MessengerOuterClass.Username) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.TopicBatch>) responseObserver);
          break;
        case METHODID_LIST_SUBSCRIBER:
          serviceImpl.listSubscriber((MessengerOuterClass.Topic) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.UsernameBatch>) responseObserver);
          break;
        case METHODID_LIST_MESSAGE_BY_USER:
          serviceImpl.listMessageByUser((MessengerOuterClass.Username) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.MessageIdBatch>) responseObserver);
          break;
        case METHODID_LIST_MESSAGE_BY_TOPIC:
          serviceImpl.listMessageByTopic((MessengerOuterClass.Topic) request,
              (io.grpc.stub.StreamObserver<MessengerOuterClass.MessageIdBatch>) responseObserver);
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

  private static abstract class MessengerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessengerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return MessengerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Messenger");
    }
  }

  private static final class MessengerFileDescriptorSupplier
      extends MessengerBaseDescriptorSupplier {
    MessengerFileDescriptorSupplier() {}
  }

  private static final class MessengerMethodDescriptorSupplier
      extends MessengerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessengerMethodDescriptorSupplier(String methodName) {
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
      synchronized (MessengerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessengerFileDescriptorSupplier())
              .addMethod(getStoreMessageMethod())
              .addMethod(getStoreMethod())
              .addMethod(getRetrieveMessageMethod())
              .addMethod(getRetrieveMessagesMethod())
              .addMethod(getDeleteMessageMethod())
              .addMethod(getDeleteMessagesMethod())
              .addMethod(getSubscribeMethod())
              .addMethod(getUnsubscribeMethod())
              .addMethod(getListUserMethod())
              .addMethod(getListTopicMethod())
              .addMethod(getListTopicByUserMethod())
              .addMethod(getListSubscriberMethod())
              .addMethod(getListMessageByUserMethod())
              .addMethod(getListMessageByTopicMethod())
              .build();
        }
      }
    }
    return result;
  }
}
