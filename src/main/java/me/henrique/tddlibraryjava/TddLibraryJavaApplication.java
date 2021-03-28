package me.henrique.tddlibraryjava;

import me.henrique.tddlibraryjava.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class TddLibraryJavaApplication {

	@Autowired
	private EmailService emailService;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// executa teste assim que a aplicação for iniciada
//	@Bean
//	public CommandLineRunner runner() {
//		return args -> {
//			List<String> emails = Arrays.asList("7784f77e12-a221e8@inbox.mailtrap.io");
//			emailService.sendMails("Testando serviço de emails", emails);
//			System.out.println("Emails enviados");
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(TddLibraryJavaApplication.class, args);
	}

}
