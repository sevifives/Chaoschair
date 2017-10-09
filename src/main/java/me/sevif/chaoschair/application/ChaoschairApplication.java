package me.sevif.chaoschair.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@ComponentScan({"me.sevif.chaoschair.controllers"})
@EnableElasticsearchRepositories({"me.sevif.chaoschair.db.repository"})
public class ChaoschairApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaoschairApplication.class, args);
	}
}
