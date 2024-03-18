package com.revature.demomongospring;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.revature.demomongospring.models.GenericRecord;
import com.revature.demomongospring.models.OtherGenericRecord;
import com.revature.demomongospring.repositories.GenericRecordRepository;
import com.revature.demomongospring.repositories.OtherGenericRecordRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
		"com.revature.demomongospring.controllers",
		"com.revature.demomongospring.services",
		"com.revature.demomongospring.repositories"},
		exclude = {DataSourceAutoConfiguration.class}
)
public class DemoMongoSpringApplication {

	/**
	 * This method will help fix the problem where Jackson serializes the ID field as an ID object with timestamp and date.
	 * Spring will already create a bean to do this, we are specifying a more specific bean here that spring will use
	 * instead. There are multiple ways to get our spring application to use this behavior. We could also use the
	 * @MongoId annotation with the proper attribute on the model class, like this: @MongoId(FieldType.OBJECT_ID).
	 * See Associate class.
	 * @return
	 */
	@Bean(name = "JacksonMongoObjectIdSerializer")
	public Jackson2ObjectMapperBuilderCustomizer customizer(){
		return builder -> builder.serializerByType(ObjectId.class, new ToStringSerializer());
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoMongoSpringApplication.class, args);

		OtherGenericRecordRepository repo = ctx.getBean(OtherGenericRecordRepository.class);
		OtherGenericRecord testDoc = new OtherGenericRecord();
		testDoc.append("manufacturer", "toyota");
		testDoc.append("model", "camry");
		testDoc.append("year", "2024");
		repo.save(testDoc);


	}

}
