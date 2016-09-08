package com.dev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/spring_security_check")
                .defaultSuccessUrl("/books", true)
                .failureUrl("/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource, PasswordEncoder passwordEncoder) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT name, password, true FROM users WHERE name=?")
                .authoritiesByUsernameQuery("SELECT name, role FROM users WHERE name=?")
                .passwordEncoder(passwordEncoder);
    }

    /**
     * Enable method security.
     */
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    }
}
