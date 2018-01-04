package hm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "hm")
@EnableMongoRepositories(basePackages = "hm")
@EntityScan(basePackages = "hm")
@EnableScheduling
public class Application {

	@Value("${configuration.phantomjs.binary.path}")
	private String phantomJSPath;
	@Value("${configuration.selenium.server}")
	private String seleniumServer;

	public static void main(String[] args) {
		System.setProperty("timestamp", Helper.now(Helper.DEFAULT_FORMAT_DATE));
		SpringApplication.run(Application.class, args);
	}
}
