package Hahn.ApplicationProcess.Aplication.Aufgabe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"Hahn.ApplicationProcess"})
@EnableJpaRepositories({"Hahn.ApplicationProcess"})
@Configuration
@EnableAutoConfiguration
@EntityScan({"Hahn.ApplicationProcess"})
public class AufgabeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AufgabeApplication.class, args);
	}

}
