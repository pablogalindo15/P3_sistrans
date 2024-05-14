package proyecto3.BacAndes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class BacAndesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BacAndesApplication.class, args);
	}

}
