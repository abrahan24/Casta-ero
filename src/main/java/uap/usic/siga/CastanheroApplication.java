package uap.usic.siga;

import java.util.Collections;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class CastanheroApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CastanheroApplication.class); 
		app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/Castanhero"));
		app.run(args);
	}

}
