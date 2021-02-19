package ergasia.katanemhmena.system.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ergasia.katanemhmena.system.enums.*;

import ergasia.katanemhmena.system.entities.*;
import ergasia.katanemhmena.system.services.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	GeneralMemberService gmService;
	@Autowired
	PhDStudentService phdService;
	@Autowired
	SupervisorService supervisorService;
	@Autowired
	SecreteryService secService;
	@Autowired
	PasswordEncoder passwordEncode;

	@GetMapping("/AdminEntry")
	public String AdminEntry() {
		return "AdminEntry";
	}

	@GetMapping("/Users")
	public String UsersList(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("reqUser", new User());
		return "Users";
	}
	@GetMapping("/NewUser")
	public String NewUser(Model model) {
		model.addAttribute("reqUser", new User());
		return "NewUser";
	}
	@PostMapping("/AddUser")
	public String register(@Valid @ModelAttribute("reqUser") User reqUser, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "NewUser";
		}
		User user = userService.findByUserName(reqUser.getUsername());
		if (user != null) {
			redirectAttributes.addFlashAttribute("saveUser", "exist-name");
			return "redirect:/admin/NewUser";
		}
		user = userService.findByEmail(reqUser.getEmail());
		if (user != null) {
			redirectAttributes.addFlashAttribute("saveUser", "exist-email");
			return "redirect:/admin/NewUser";
		}
		reqUser.setPassword(passwordEncode.encode(reqUser.getPassword()));
		if (reqUser.getRole().equals(Role.ROLE_GENERALMEMBER)) {
			General_member user1 = new General_member(reqUser.getRole(), reqUser.getUsername(), reqUser.getPassword(),
					reqUser.getEmail(), reqUser.getFull_name(), reqUser.getPhone());
			;
			if (userService.save(user1) != null) {
				redirectAttributes.addFlashAttribute("saveUser", "success");
			} else {
				redirectAttributes.addFlashAttribute("saveUser", "fail");
			}
		} else if (reqUser.getRole().equals(Role.ROLE_PHDSTUDENT)) {
			PhDStudent user1 = new PhDStudent(reqUser.getRole(), reqUser.getUsername(), reqUser.getPassword(),
					reqUser.getEmail(), reqUser.getFull_name(), reqUser.getPhone());
			;
			if (userService.save(user1) != null) {
				redirectAttributes.addFlashAttribute("saveUser", "success");
			} else {
				redirectAttributes.addFlashAttribute("saveUser", "fail");
			}
		} else if (reqUser.getRole().equals(Role.ROLE_SECRETERY)) {
			Secretery user1 = new Secretery(reqUser.getRole(), reqUser.getUsername(), reqUser.getPassword(),
					reqUser.getEmail(), reqUser.getFull_name(), reqUser.getPhone());
			;
			if (userService.save(user1) != null) {
				redirectAttributes.addFlashAttribute("saveUser", "success");
			} else {
				redirectAttributes.addFlashAttribute("saveUser", "fail");
			}
		} else if (reqUser.getRole().equals(Role.ROLE_SUPERVISOR)) {
			Supervisor user1 = new Supervisor(reqUser.getRole(), reqUser.getUsername(), reqUser.getPassword(),
					reqUser.getEmail(), reqUser.getFull_name(), reqUser.getPhone());
			;
			if (userService.save(user1) != null) {
				redirectAttributes.addFlashAttribute("saveUser", "success");
			} else {
				redirectAttributes.addFlashAttribute("saveUser", "fail");
			}
		}
		System.out.println(reqUser);
		return "redirect:/admin/NewUser";
	}

	@GetMapping("/get/{id}")
	public String getUser(Model model, @PathVariable("id") int id) {
		User user = userService.findById(id);
		System.out.println(user);
		model.addAttribute("user", user);
		return "ModifyUser";
	}

	@PostMapping("/get/modify/{id}")
	public String ModifyUser(@PathVariable("id") int id,@Valid  @ModelAttribute("user") User reqUser,BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		System.out.println(reqUser);
		if (bindingResult.hasErrors()) {
			return "ModifyUser";
		}
		User user = userService.findById(id);
		if (!reqUser.getUsername().equals(user.getUsername())) {
			User user1 = userService.findByUserName(reqUser.getUsername());
			if (user1 != null) {
				redirectAttributes.addFlashAttribute("saveUser", "exist-name");
				return "redirect:/admin/get/"+id;
			}
		}
		if (!reqUser.getEmail().equals(user.getEmail())) {
			User user2 = userService.findByEmail(reqUser.getEmail());
			if (user2 != null) {
				redirectAttributes.addFlashAttribute("saveUser", "exist-email");
				return "redirect:/admin/get/"+id;
			}
		}
		user.setPhone(reqUser.getPhone());
		user.setEmail(reqUser.getEmail());
		user.setFull_name(reqUser.getFull_name());
		user.setUsername(reqUser.getUsername());
		userService.update(user);
		redirectAttributes.addFlashAttribute("modifyUser", "success");
		return "redirect:/admin/Users";
	}

	@GetMapping("/DeleteUser/{id}")
	public String DeleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		userService.delete(id);
		redirectAttributes.addFlashAttribute("message", "deleted");
		return "redirect:/admin/Users";
	}

}
