package com.example.Aser;


import io.vertx.core.Vertx;
public class MainDev {


    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(rq -> {
            rq.response().end("Heelo from async code");
        }).listen(8090 , server -> {
            if(server.succeeded()) {
                System.out.println("Server srart with this port 8090");
            }else {
                System.err.println("Server failed with this port 8090");
            }
        });
        // графана graphQl git docker gitlab vs jenkins
        // kubernaets RabitMQ redis Scala/ Java SOLID KISS mongo 2.7 3.3
        // гибкость языка
    }
}
