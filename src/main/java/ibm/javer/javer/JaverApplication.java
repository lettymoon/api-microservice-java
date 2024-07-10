package ibm.javer.javer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JaverApplication {
	@Autowired
	@Qualifier("applicationName")
	private String applicationName;

	@GetMapping("/banco-javer")
	public String BancoJaver(){
		return applicationName;
	}

	public static void main(String[] args) {
		SpringApplication.run(JaverApplication.class, args);
	}
}
