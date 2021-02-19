package ergasia.katanemhmena.system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ergasia.katanemhmena.system.services.UserService;
import ergasia.katanemhmena.system.entities.User;
import ergasia.katanemhmena.system.enums.Role;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncode;

    @Override
    public void run(String...args) throws Exception {
        if (userService.findAll().isEmpty()) {
			User admin = new User(Role.ROLE_ADMIN, "admin", passwordEncode.encode("admin"), "admin@gmail.com",
					"admin stev", "6976986754");
                    userService.save(admin);
		}
    }
}