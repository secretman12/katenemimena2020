package ergasia.katanemhmena.system.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import ergasia.katanemhmena.system.security.jwt.JwtTokenFilterConfigurer;
import ergasia.katanemhmena.system.security.jwt.JwtTokenProvider;
import ergasia.katanemhmena.system.security.jwt.MyUserDetails;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder;
    }
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
	DataSource dataSource;
    @Autowired
    MyUserDetails customUserDetailsService;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public  void WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
	   @Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	           auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
	                           .usersByUsernameQuery("select username,password,enabled from users where username=?")
	                           .authoritiesByUsernameQuery("select username,role from users where username=?");
       }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		        .csrf().disable()
		        .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/secretery/**").hasRole("SECRETERY")
                .antMatchers("/generalMember/**").hasRole("GENERALMEMBER")
                .antMatchers("/supervisor/**").hasRole("SUPERVISOR")
                .antMatchers("/","/login","/static/**","/css/**","/img/**","/js/**").permitAll()
                .antMatchers( "/","/login", "/webjars/**", "/static/**","/css/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)
                 .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
        	    .exceptionHandling().accessDeniedPage("/403");
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }


}