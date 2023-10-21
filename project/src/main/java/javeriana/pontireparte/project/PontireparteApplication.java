package javeriana.pontireparte.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PontireparteApplication {

	protected static final Logger logger = LoggerFactory.getLogger(PontireparteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PontireparteApplication.class, args);
		logger.info("*** Ponti-reparte application started ***");
	}

}
