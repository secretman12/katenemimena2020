package ergasia.katanemhmena.system.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorHandler {
	@RequestMapping(value = "/400")
	public String error400(Model model) {
		model.addAttribute("errorMsg", "400");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream().map(item -> item.getAuthority()).findFirst().get().toString();		model.addAttribute("role", auth.getAuthorities());
        model.addAttribute("role", role);
		return "error";
	}

	@RequestMapping(value = "/404")
	public String error404(Model model) {
		model.addAttribute("errorMsg", "404");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream().map(item -> item.getAuthority()).findFirst().get().toString();		model.addAttribute("role", auth.getAuthorities());
        model.addAttribute("role", role);
		return "error";
	}
	@RequestMapping(value = "/403")
	public String error403(Model model) {
		model.addAttribute("errorMsg", "403");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().stream().map(item -> item.getAuthority()).findFirst().get().toString();		model.addAttribute("role", auth.getAuthorities());
        model.addAttribute("role", role);
        return "error";
	}

	@RequestMapping(value = "/500")
	public String error500(Model model) {
		model.addAttribute("errorMsg", "500");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream().map(item -> item.getAuthority()).findFirst().get().toString();		model.addAttribute("role", auth.getAuthorities());
        model.addAttribute("role", role);
		return "error";
	}
}
