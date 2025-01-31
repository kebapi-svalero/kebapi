package org.kebapi.kebapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class KebapiApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(KebapiApiApplication.class, args);
  }
}
