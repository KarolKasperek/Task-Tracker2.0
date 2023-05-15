package com.TaskTracker.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static com.TaskTracker.enums.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/css/**", "/images/**", "/js/**", "/sign-up", "/sign-up").permitAll()
//                .requestMatchers("/api/**", "management/api/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.DELETE).hasAuthority(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic();
                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/sign-in")
                                        .loginProcessingUrl("/sign-in")
                                        .defaultSuccessUrl("/")
                                        .permitAll());

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
                .username("karol05ks@gmail.com")
                .password(passwordEncoder.encode("32131212AQ"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
