package ergasia.katanemhmena.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"ergasia.katanemhmena.system"})
public class MonitoringPhDStudentsTaskSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringPhDStudentsTaskSystemApplication.class, args);
	}

}
