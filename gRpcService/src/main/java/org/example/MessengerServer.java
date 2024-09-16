package org.example;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class MessengerServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new MessengerServiceImpl())
                .maxInboundMessageSize(1024 * 1024 * 1024)
                .build();

        server.start();
        System.out.println("Server started on port 50051");

        server.awaitTermination();
    }
}