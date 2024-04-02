package com.taskTracker.config.security;

import com.taskTracker.handler.AccessDeniedHandler;
import com.taskTracker.service.impl.RegisterServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration {
    private final RegisterServiceImpl registerServiceImpl;

    @Bean
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .userDetailsService(registerServiceImpl)
                .authorizeHttpRequests()
                .requestMatchers("/style/**", "/img/**", "/script/**", "/sign-in", "/sign-up").permitAll()
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
