package ua.com.krupet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by krupet on 7/26/15.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Have to disable it for POST methods:
        // http://stackoverflow.com/a/20608149/1199132
        http.csrf().disable();

        // Logout and redirection:
        // http://stackoverflow.com/a/24987207/1199132
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/index.xhtml");

        http.authorizeRequests()
                // Some filters enabling url regex:
                // http://stackoverflow.com/a/8911284/1199132
                .regexMatchers(
                        "/index.xhtml")
                .permitAll()
                        //Permit access for all to error and denied views
                .antMatchers("/denied.xhtml")
                .permitAll()
                        // Only access with admin role
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                        //Permit access only for some roles
                .antMatchers("/manag/**")
                .hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/usr/**")
                .hasAnyRole("ADMIN", "MANAGER", "USER")
                        //If newUser doesn't have permission, forward him to login page
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/login")
//                .loginProcessingUrl("/login.xhtml")
                .defaultSuccessUrl("/usr/shop.xhtml")
                .and().exceptionHandling().accessDeniedPage("/denied.xhtml");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Configure roles and passwords as in-memory authentication
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");
//        auth.inMemoryAuthentication()
//                .withUser("manager")
//                .password("manager")
//                .roles("MANAGER");
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .roles("USER");

//        JDBC authentication
//        auth.jdbcAuthentication().dataSource(dataSource)
//        .usersByUsernameQuery(
//                "select username,password, enabled from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, role from user_roles where username=?");

        /*
            dirty hack: user must contain 'enabled' column so instead i selecting 1
            TODO: make all properly (provide 'enabled' column or UserDetailsService)
         */
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select user_login, user_password, 1 from users where user_login=?")
                .authoritiesByUsernameQuery(
                        "select user_name, user_role from user_roles where user_name=?");
    }
}
