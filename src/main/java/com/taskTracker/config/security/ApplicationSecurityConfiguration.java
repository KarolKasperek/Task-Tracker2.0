package com.taskTracker.config.security;

import com.taskTracker.handler.AccessDeniedHandler;
import com.taskTracker.service.RegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static com.taskTracker.enums.Role.ADMIN;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration {
    private final RegisterService registerService;

    public ApplicationSecurityConfiguration(RegisterService registerService) {
        this.registerService=registerService;
    }

    @Bean
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .userDetailsService(registerService)
                .authorizeHttpRequests()
                .requestMatchers("/css/**", "/images/**", "/js/**", "/sign-up", "/sign-in").permitAll()
//                .requestMatchers("/api/**", "management/api/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.DELETE).hasAuthority(ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler())
                .and()
                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/sign-in")
                                        .defaultSuccessUrl("/")
                                        .loginProcessingUrl("/sign-in")
                                        );

        return http.build();
    }
}
