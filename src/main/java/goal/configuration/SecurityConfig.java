package goal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import goal.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/user/**").authenticated()
        .antMatchers("/**").permitAll();

		http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/view/home/login_home")
        .permitAll();

//		http.logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .logoutSuccessUrl("/login")
//        .invalidateHttpSession(true);

		http.exceptionHandling()
        .accessDeniedPage("/view/error/denied");
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}
}
