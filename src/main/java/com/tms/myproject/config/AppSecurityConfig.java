package com.tms.myproject.config;

import com.tms.myproject.config.provider.AppMyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppMyProvider appMyProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      http.authorizeRequests().antMatchers("/main").hasAnyRole("USER");
            http.authorizeRequests()
                    .antMatchers("/saveUser").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").authenticated()
//                    .anyRequest().permitAll()
                    .and()
                    .formLogin().loginPage("/loginUser").permitAll().defaultSuccessUrl("/getAllNews", true)
                    .and()
                    .exceptionHandling().accessDeniedPage("/403");

        http.csrf()
                .disable()
                .authorizeRequests();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(appMyProvider);

    }
}
