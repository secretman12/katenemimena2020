package ergasia.katanemhmena.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ergasia.katanemhmena.system.entities.General_member;
import ergasia.katanemhmena.system.entities.PhDStudent;
import ergasia.katanemhmena.system.entities.Secretery;
import ergasia.katanemhmena.system.entities.Supervisor;
import ergasia.katanemhmena.system.entities.User;
import ergasia.katanemhmena.system.services.GeneralMemberService;
import ergasia.katanemhmena.system.services.PhDStudentService;
import ergasia.katanemhmena.system.services.SupervisorService;
import ergasia.katanemhmena.system.services.UserService;
import ergasia.katanemhmena.system.enums.General_member_type;
import ergasia.katanemhmena.system.enums.Role;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	GeneralMemberService gmService;
	@Autowired
	SupervisorService supervisorService;
	@Autowired
	PasswordEncoder passwordEncode;
	@Autowired
	PhDStudentService phdService;

	@GetMapping("/")
	public String Home(Model model) {
		if (userService.findAll().isEmpty()) {
			User phd = new User(Role.ROLE_ADMIN, "admin", passwordEncode.encode("admin"), "admin@gmail.com",
					"admin pap", "6976986754");
			userService.save(phd);
		}
		model.addAttribute("reqUser", new User());
		return "login";
	}

	@GetMapping("/login")
	public String Userlogin(Model model) {
		model.addAttribute("reqUser", new User());

		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}
}
