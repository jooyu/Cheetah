package org.yujoo.baas.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.yujoo.baas")
public class CheethSpringBoot {
	public static void main(String[] args) {
		SpringApplication.run(CheethSpringBoot.class, args);
	}
}