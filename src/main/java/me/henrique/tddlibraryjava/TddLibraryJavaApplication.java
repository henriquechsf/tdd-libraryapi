package me.henrique.tddlibraryjava;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class TddLibraryJavaApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Scheduled(cron = "0 0 18 1/1 * ?")
	public void testeAgendamento() {
		System.out.println("Teste agendadmento de tarefas");
	}

	public static void main(String[] args) {
		SpringApplication.run(TddLibraryJavaApplication.class, args);
	}

}
