package ergasia.katanemhmena.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ergasia.katanemhmena.system.entities.*;
import ergasia.katanemhmena.system.services.*;

@Controller
@RequestMapping("/secretery")
public class SecreteryController {
	@Autowired
	SupervisorService supervisorService;
	@Autowired
	PhDStudentService phdService;
	@Autowired
	TaskService taskService;

	@GetMapping("/secreteryEntry")
	public String SecreteryEntry() {
		return "SecreteryEntry";
	}

	@GetMapping("/supervisors")
	public String Supervisors(Model model) {
		model.addAttribute("supervisors", supervisorService.findAll());
		return "Supervisors";
	}

	@GetMapping("/phDStudents")
	public String phDStudents(Model model) {
		model.addAttribute("phDStudents", phdService.findAll());
		return "PhDStudents";
	}

	@GetMapping("/getSupervisor/{id}")
	public String getSupervisor(Model model, @PathVariable("id") int id) {
		Supervisor supervisor = supervisorService.findById(id);
		System.out.println(supervisor);
		model.addAttribute("supervisor", supervisor);
		model.addAttribute("phDStudents", phdService.findAll());
		return "SupervisorDetails";
	}

	@PostMapping("/addPhD/{id}")
	public String AddPhDStudentTOSupervisor(Model model, @PathVariable("id") int id, @RequestParam("id") int id2,
			RedirectAttributes redirectAttributes) {
		Supervisor supervisor = supervisorService.findById(id);
		PhDStudent phd = phdService.findById(id2);
		supervisor.setPhdStudent(phd);
		supervisorService.update(supervisor);
		redirectAttributes.addFlashAttribute("addPhd", "success");
		return "redirect:/secretery/getSupervisor/" + id;
	}

	@GetMapping("/getPhDStudent/{id}")
	public String getPhDStudent(Model model, @PathVariable("id") int id) {
		PhDStudent phd = phdService.findById(id);
		System.out.println(phd);
		if (phd.getTask() != null) {
			Task task = taskService.findById(phd.getTask().getId());
			model.addAttribute("task", task);
		}
		model.addAttribute("phd", phd);
		return "PhDStudentDetails";
	}

	@PostMapping("/getPhDStudent/RegisterPoints/{id}")
	public String pointsRegister(Model model, @PathVariable("id") int id, @ModelAttribute("task") Task task,
			RedirectAttributes redirectAttributes) {
		PhDStudent phd = phdService.findById(id);
		taskService.update(task);
		phd.setSurveillance_hours(0);
		phd.setXp_per_task(0);
		phd.setTeach_lab_hours(0);
		phdService.update(phd);
		redirectAttributes.addFlashAttribute("addTask", "success");
		return "redirect:/secretery/getPhDStudent/" + id;
	}
}
