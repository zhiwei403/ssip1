package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
  	.csrf().disable()
  	.authorizeRequests()
  	.antMatchers("/", "/home").permitAll()    
  	.antMatchers("/camera/list").permitAll()
  	.antMatchers("/camera2/list2").permitAll()
  	.antMatchers("/list3").permitAll()
  	.antMatchers("/camera/store").permitAll()
  	.antMatchers("/camera2/store2").permitAll()
  	.antMatchers("/store3").permitAll()
  	.antMatchers("/error").permitAll()
  	.antMatchers("/register").permitAll()
  	.antMatchers("/viewall").access("hasRole('ADMIN')")
  	.antMatchers("/success").permitAll()
  	.antMatchers("/imageview", "/hello").access("hasRole('USER')")
	.antMatchers("/imageview2", "/hello2").access("hasRole('ADMIN')").and()
		.formLogin().and().exceptionHandling()
		.accessDeniedPage("/access-denied")

	    .and()
	.formLogin()
	    .loginPage("/login").permitAll()
	   .defaultSuccessUrl("/",true) //Force user to always go to the home page.
	    .and()
	.logout()
	    .permitAll();
  

          
  }
}


@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	
@Autowired
	  AccountRepository accountRepository;
  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService())
    .passwordEncoder(passwordEncoder()); //bcrypt is hashing, it decrypt the password
   
    auth
    .inMemoryAuthentication()
    .passwordEncoder(passwordEncoder()) //bcrypt is hashing, it decrypt the password
    .withUser("admin").password("$2a$10$BqmD5O7An1LwP7BaZenzful5XA82Kt4h0E/U93Hwmd3v0AGLTsl0e").roles("USER", "ADMIN");
  //  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	//    String encodedPassword = passwordEncoder.encode("admin");  
	  // System.out.print(encodedPassword);
  }
  
  
  @Bean
	public BCryptPasswordEncoder passwordEncoder(){
	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
  
  
  
  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account != null) {
        return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + username + "'");
        }
      }     
    };
  }
}

