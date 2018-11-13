package com.kft.oms.config;

import com.kft.security.service.CustomUserDetailService;
import com.kft.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    SecurityService securityService;

    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(securityService.getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry=http.authorizeRequests();

       registry.antMatchers("/css/**","/js/**","/img/**","/build/**","/dist/**","/plugins/**","/dist/img/**").permitAll()
                .antMatchers("/login").anonymous();

        registry.anyRequest().permitAll();
               /* registry.anyRequest().authenticated()
                        .and()
                        .formLogin().loginPage("/login").defaultSuccessUrl("/home")
                        .usernameParameter("username").passwordParameter("password")
                        .and()
                         .logout().logoutSuccessUrl("/login?logout")
                        .and()
                        .exceptionHandling().accessDeniedPage("/403")
                        .and()
                        .csrf().disable();*/

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
