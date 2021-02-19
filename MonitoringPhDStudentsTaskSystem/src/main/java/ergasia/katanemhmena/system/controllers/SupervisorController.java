package ergasia.katanemhmena.system.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ergasia.katanemhmena.system.entities.PhDStudent;
import ergasia.katanemhmena.system.entities.Supervisor;
import ergasia.katanemhmena.system.entities.Task;
import ergasia.katanemhmena.system.services.PhDStudentService;
import ergasia.katanemhmena.system.services.SupervisorService;
import ergasia.katanemhmena.system.services.TaskService;
import ergasia.katanemhmena.system.enums.*;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
	@Autowired
	SupervisorService supervisorService;
	@Autowired
	PhDStudentService phdService;
	@Autowired
	TaskService taskService;

	@GetMapping("/supervisorEntry")
	public String SecreteryEntry() {

		return "SupervisorEntry";
	}
	@GetMapping("/myphDStudent")
	public String phDStudents(Model model,Principal user) {
		Supervisor current_supervisor = supervisorService.findByUserName(user.getName());
		model.addAttribute("phd", current_supervisor.getPhdStudent());
		model.addAttribute("tasks",taskService.findByStatus(Status.Unregistered));
		return "MyPhDStudent";
	}
	@PostMapping("/addTask")
	public String AddPhDStudentTOSupervisor(Model model,@RequestParam("id") int id,Principal user,RedirectAttributes redirectAttributes) {
		Supervisor current_supervisor = supervisorService.findByUserName(user.getName());
		PhDStudent phd = current_supervisor.getPhdStudent();
		Task task = taskService.findById(id);
		task.setStatus(Status.Registered);
		phd.setTask(task);
		phdService.update(phd);
		redirectAttributes.addFlashAttribute("addTask", "success");
		return "redirect:/supervisor/myphDStudent";
	}
}
