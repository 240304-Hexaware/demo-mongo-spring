package com.revature.demomongospring;

import com.revature.demomongospring.models.Associate;
import com.revature.demomongospring.repositories.MyRepository;
import com.revature.demomongospring.services.MyService;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
		"com.revature.demomongospring.controllers",
		"com.revature.demomongospring.services",
		"com.revature.demomongospring.repositories"},
		exclude = {DataSourceAutoConfiguration.class}
)
public class DemoMongoSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoMongoSpringApplication.class, args);

		MyRepository myRepository = ctx.getBean(MyRepository.class);
		MyService myService = ctx.getBean(MyService.class);


		//Document doc = new Document("key", "value");
		Associate associate = new Associate("Kyle", "Plummer");
		myRepository.save(associate);


	}

}
