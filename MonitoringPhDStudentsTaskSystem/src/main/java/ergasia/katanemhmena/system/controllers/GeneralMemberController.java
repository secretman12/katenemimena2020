package ergasia.katanemhmena.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ergasia.katanemhmena.system.entities.Task;
import ergasia.katanemhmena.system.services.TaskService;
import ergasia.katanemhmena.system.enums.*;

@Controller
@RequestMapping("/generalMember")
public class GeneralMemberController {
	@Autowired
	TaskService taskService;

	@GetMapping("/generalMemberEntry")
	public String GmEntry() {
		return "GmEntry";
	}

	@GetMapping("/tasks")
	public String Taks(Model model) {
		model.addAttribute("tasks", taskService.findByStatus(Status.Unregistered));
		model.addAttribute("task", new Task());
		return "Tasks";
	}

	@PostMapping("/newTask")
	public String NewTask(@ModelAttribute("task") Task task, final RedirectAttributes redirectAttributes) {
		if (task.getGrading() + task.getSurveillance() + task.getTeach_lab() != 80) {
			redirectAttributes.addFlashAttribute("saveTask", "not80");
			return "redirect:/generalMember/tasks";
		} else {
			taskService.save(task);
			redirectAttributes.addFlashAttribute("saveTask", "success");
			return "redirect:/generalMember/tasks";
		}
	}

	@GetMapping("/DeleteTask/{id}")
	public String DeleteTask(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		taskService.delete(id);
		redirectAttributes.addFlashAttribute("deleteTask", "success");
		return "redirect:/generalMember/tasks";
	}

	@GetMapping("/getTask/{id}")
	public String getTask(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		Task task = taskService.findById(id);
		System.out.println(task);
		redirectAttributes.addFlashAttribute("reqTask", task);
		return "redirect:/generalMember/tasks";
	}

	@PostMapping("/get/modifyTask/{id}")
	public String ModifyTask(@PathVariable("id") int id, @ModelAttribute("reqTask") Task reqTask,
			RedirectAttributes redirectAttributes) {
		if (reqTask.getGrading() + reqTask.getSurveillance() + reqTask.getTeach_lab() != 80) {
			redirectAttributes.addFlashAttribute("saveTask", "not80");
			return "redirect:/generalMember/tasks";
		} else {
			taskService.update(reqTask);
			redirectAttributes.addFlashAttribute("modeifyTask", "success");
		}
		return "redirect:/generalMember/tasks";
	}

}
