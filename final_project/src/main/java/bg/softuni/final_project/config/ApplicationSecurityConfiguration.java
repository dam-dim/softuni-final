package bg.softuni.final_project.config;

import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                 .authorizeRequests()
                 .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                 .antMatchers(
                         "/admin",
                         "/services/courses/add",
                         "/services/dives/add").hasRole(UserRoleEnum.ADMIN.name())
                 .antMatchers("/reviews/add").hasRole(UserRoleEnum.USER.name())
                 .antMatchers(
                         "/services/courses/*/edit",
                         "/services/courses/*/edit/errors",
                         "/services/dives/*/edit",
                         "/services/dives/*/edit/errors").hasAnyRole(UserRoleEnum.ADMIN.name(), UserRoleEnum.ADMINISTRATOR.name())
                 .antMatchers(
                         "/",
                         "/users/*",
                         "/services",
                         "/services/*",
                         "/services/dives/*/details",
                         "/services/courses/*/details",
                         "/reviews").permitAll()
                 .antMatchers("/**").authenticated()
                .and()
                 .formLogin()
                 .loginPage("/users/login")
                 .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                 .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                 .defaultSuccessUrl("/")
                 .failureForwardUrl("/users/login-error")
                .and()
                 .logout()
                 .logoutUrl("/users/logout")
                 .logoutSuccessUrl("/")
                 .invalidateHttpSession(true)
                 .deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
