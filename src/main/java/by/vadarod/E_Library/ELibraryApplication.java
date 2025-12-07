package by.vadarod.E_Library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class ELibraryApplication {


	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ELibraryApplication.class, args);

	}

}
