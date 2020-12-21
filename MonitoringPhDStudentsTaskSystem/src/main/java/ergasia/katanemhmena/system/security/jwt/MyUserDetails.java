package ergasia.katanemhmena.system.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ergasia.katanemhmena.system.entities.PhDStudent;
import ergasia.katanemhmena.system.services.PhDStudentService;



@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private PhDStudentService phdService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final PhDStudent user = phdService.findByUserName(username);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(user.getPassword())//
        .authorities(user.getRole().toString())
        .username(user.getUsername())
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
