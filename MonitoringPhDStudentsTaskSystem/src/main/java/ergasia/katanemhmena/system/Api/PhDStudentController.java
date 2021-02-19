package ergasia.katanemhmena.system.Api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ergasia.katanemhmena.system.entities.PhDStudent;
import ergasia.katanemhmena.system.exception.CustomException;
import ergasia.katanemhmena.system.security.jwt.JwtTokenProvider;
import ergasia.katanemhmena.system.services.PhDStudentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/phdStudent")
public class PhDStudentController {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private PhDStudentService phdService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json", "application/xml" })
	public String login(@RequestBody LoginRequest loginRequest) {
		try {
			PhDStudent phd = phdService.findByUserName(loginRequest.getUsername());
			if (phd == null) {
				throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				System.out.println(authentication.getPrincipal());
				return jwtTokenProvider.createToken(loginRequest.getUsername());
			}
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@RequestMapping(value = "/whoami", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public PhDStudent whoami(HttpServletRequest req) {
		PhDStudent phd = phdService.findByUserName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
		System.out.println(phd);
		return phd;
	}
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT, produces = { "application/json", "application/xml" })
	public PhDStudent GetPhDTask(@RequestBody PhDStudent phdStudent,@PathVariable("id") int id) {
		PhDStudent phd =phdService.findById(id);
		phd.setTeach_lab_hours(phd.getTeach_lab_hours()+phdStudent.getTeach_lab_hours());
		phd.setXp_per_task(phd.getXp_per_task()+phdStudent.getXp_per_task());
		phd.setSurveillance_hours(phd.getSurveillance_hours()+phdStudent.getSurveillance_hours());
		phdService.update(phd);
		return phd;
	}
}
