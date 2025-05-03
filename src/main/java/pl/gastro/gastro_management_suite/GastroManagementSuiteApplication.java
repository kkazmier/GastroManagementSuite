package pl.gastro.gastro_management_suite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import pl.gastro.gastro_management_suite.config.SecurityConfig;

@SpringBootApplication
public class GastroManagementSuiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastroManagementSuiteApplication.class, args);
	}

}
