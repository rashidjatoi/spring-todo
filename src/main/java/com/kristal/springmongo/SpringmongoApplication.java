package com.kristal.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kristal.springmongo.Config")
public class SpringmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmongoApplication.class, args);
	}

}
