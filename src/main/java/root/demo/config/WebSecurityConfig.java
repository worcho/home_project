package root.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register","/login", "/css/*", "/js/*").permitAll()
                .antMatchers("/dishes").hasAnyRole("ADMIN")
                .antMatchers("/restaurants").hasAnyRole("ADMIN")
                .antMatchers("/orders").hasAnyRole("ADMIN")
                .antMatchers("/users").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .loginPage("/login")
                .passwordParameter("password")
                .usernameParameter("email")
                .successForwardUrl("/")
                .and().userDetailsService(userDetailsService)
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/accessError");


    }
}
