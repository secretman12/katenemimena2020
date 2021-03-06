package ergasia.katanemhmena.system.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			httpServletResponse.sendRedirect("/admin/AdminEntry");
		} else if (roles.contains("ROLE_GENERALMEMBER")) {
			httpServletResponse.sendRedirect("/generalMember/generalMemberEntry");

		}else if (roles.contains("ROLE_SECRETERY")) {
			httpServletResponse.sendRedirect("/secretery/secreteryEntry");
		}else if (roles.contains("ROLE_PHDSTUDENT")) {
			SecurityContextHolder.getContext().setAuthentication(null);
			httpServletResponse.sendRedirect("/login?error=true");

		}else if (roles.contains("ROLE_SUPERVISOR")) {
			httpServletResponse.sendRedirect("/supervisor/supervisorEntry");

		}
	}
}
