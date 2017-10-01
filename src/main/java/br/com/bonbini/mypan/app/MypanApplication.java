package br.com.bonbini.mypan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MypanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypanApplication.class, args);
	}
}
