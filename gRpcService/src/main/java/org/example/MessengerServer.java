package org.example;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class MessengerServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Construire le serveur
        Server server = ServerBuilder.forPort(50051)
                .addService(new MessengerServiceImpl())
                .maxInboundMessageSize(1024 * 1024 * 1024)
                .build();

        // Démarrer le serveur
        server.start();
        System.out.println("Server started on port 50051");

        // Bloquer jusqu'à la terminaison du serveur
        server.awaitTermination();
    }
}