package dev40.querydsl;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class QuerydslApplication {

    public static void main(String[] args) {
        startH2Server();
        SpringApplication.run(QuerydslApplication.class, args);
    }

    private static void startH2Server() {
        try {
            String absolutePath = new File("./db").getAbsolutePath();
            Server h2Server = Server.createTcpServer("-ifNotExists", "-baseDir", absolutePath).start();
            if (h2Server.isRunning(true)) {
                log.info("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }

}
