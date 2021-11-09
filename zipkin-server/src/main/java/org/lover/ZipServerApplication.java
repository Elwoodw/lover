package org.lover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipServerApplication.class,args);
    }
}
