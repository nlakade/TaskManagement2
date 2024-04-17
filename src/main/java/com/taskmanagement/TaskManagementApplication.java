package com.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server9090 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server9090.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
        app.run(args);
    }
}

@SpringBootApplication
public class Server9091 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server9091.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9091"));
        app.run(args);
    }
}

@SpringBootApplication
public class Server9092 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server9092.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9092"));
        app.run(args);
    }
}
