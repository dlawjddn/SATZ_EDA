package satz.event.satzeda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SatzEdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatzEdaApplication.class, args);
    }

}
