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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService customUserDetailService;

    private SecurityService securityService;

    @Autowired
    public void setCustomUserDetailService(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

//    Constructor injection causes circular dependency with Security service and hence setter based injection is used
//    @Autowired
//    public WebSecurityConfig(CustomUserDetailService customUserDetailService, SecurityService securityService) {
//        this.customUserDetailService = customUserDetailService;
//        this.securityService = securityService;
//    }

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

/*      NOTE: Code added to solve the problem of creating csrf token after some of the template is parsed and
        committed to output. When thymeleaf reaches the line to process form element it tries to create
        a new session and so it encounters exception. for more details visit
            https://github.com/thymeleaf/thymeleaf-extras-springsecurity/issues/34
            https://github.com/thymeleaf/thymeleaf-spring/issues/110
            https://github.com/spring-projects/spring-security/issues/3906
        find out the effect of always creating sessions and alternative solution especially after reducing the
        size of the layout file below 16kb
        */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
