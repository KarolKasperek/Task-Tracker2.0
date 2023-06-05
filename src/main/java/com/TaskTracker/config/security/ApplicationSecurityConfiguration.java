package com.TaskTracker.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static com.TaskTracker.enums.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration {

    @Bean
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests()
                .requestMatchers("/css/**", "/images/**", "/js/**", "/sign-up", "/sign-up","/sign-in").permitAll()
//                .requestMatchers("/api/**", "management/api/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.DELETE).hasAuthority(ADMIN.name())
                .anyRequest().authenticated()
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

    @Bean
    protected UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("a@a")
                .password(passwordEncoder.encode("1"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(admin);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
