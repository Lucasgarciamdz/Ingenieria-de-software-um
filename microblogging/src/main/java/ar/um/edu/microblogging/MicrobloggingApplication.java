package ar.um.edu.microblogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MicrobloggingApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicrobloggingApplication.class, args);
  }

}
