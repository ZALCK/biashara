package com.elgroup.biashara.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		/* http.authorizeRequests().antMatchers("/").permitAll(); */
		/*
		 * http.authorizeRequests() .antMatchers("/","/sendSimpleEmail").permitAll()
		 * .antMatchers("/manager/**","/teacher/**").hasAnyAuthority("ADMIN")
		 * .antMatchers("/classroom/**").hasAnyAuthority("ADMIN","MANAGER","TEACHER")
		 * .antMatchers("/module/list/**").denyAll() .anyRequest().authenticated()
		 * .and() .formLogin().permitAll() .and() .logout().permitAll() .and()
		 * .exceptionHandling().accessDeniedPage("/403") ;
		 */
    	http
    	.authorizeRequests()
    	.antMatchers("/authentication/**")
    	.permitAll()
    	.anyRequest().authenticated()
    	.and()
    	.formLogin()
    	.loginPage("/authentication/login")
    	.permitAll();
    }
}
