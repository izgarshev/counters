package org.pablo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.pablo.server")
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
