package com.revature.demomongospring;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.revature.demomongospring.models.Associate;
import com.revature.demomongospring.models.GenericRecord;
import com.revature.demomongospring.models.OtherGenericRecord;
import com.revature.demomongospring.models.Trainer;
import com.revature.demomongospring.repositories.AssociateRepository;
import com.revature.demomongospring.repositories.GenericRecordRepository;
import com.revature.demomongospring.repositories.OtherGenericRecordRepository;
import com.revature.demomongospring.repositories.TrainerRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		/*
		3/18/24: Added some demo stuff about setting up generic mongo documents with working spring repos
		plus being able to create valid document references. Below is some tinkering. GenericRepository
		represents a solution that will work for your project. It extends Document, so we can just use
		it like a Document, which means we can append any properties to it as key/value pairs.

		You can set up a valid reference with the @DocumentReference annotation if you are defining model
		classes, however for a generic solution we can just append a field called "reference" (or whatever
		we like) and make sure the thing we are referencing is an ObjectId. See below.

		 */

		TrainerRepository trainerRepository = ctx.getBean(TrainerRepository.class);
		AssociateRepository associateRepository = ctx.getBean(AssociateRepository.class);
		GenericRecordRepository genericRecordRepository = ctx.getBean(GenericRecordRepository.class);

		Trainer kyle = new Trainer();//trainers contain a list of references to associates


		Associate andrew = new Associate("Andrew", "Carvajal");
		List<Associate> associates = new ArrayList<>();
		associates.add(andrew);
		kyle.setAssociates(associates);

		//The referenced associates MUST BE SAVED FIRST! This way mongo has already populated the
		//object's id with the reference id.
		for(Associate a : associates) {
			associateRepository.save(a);
		}

		//now when we call this save on the trainer object, it should have the valid references
		trainerRepository.save(kyle);

		//finally we see this with GenericRecords as well. In this example the testRecord references
		//the trainer object above.
		GenericRecord testRecord = new GenericRecord();
		testRecord.append("reference", kyle.getId());
		genericRecordRepository.save(testRecord);



//		GenericRecord owner = new GenericRecord();
//		owner.append("name", "Kyle");
//
//
//		GenericRecord car = new GenericRecord();
//		car.append("manufacturer", "ford");
//		car.append("model", "taurus");
//		car.append("year", "1997");
//		car.append("owner", owner);
//
//		repo.save(car);
//		Optional<GenericRecord> optionalResult = repo.findById(new ObjectId("65f8a905574b8279f9aa4c04"));
//		GenericRecord result = optionalResult.get();
//		System.out.println(result.get("owner"));



	}

}
