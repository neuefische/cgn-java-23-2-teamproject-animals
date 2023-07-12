package de.neuefische.backend.security;


import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(httpRequests ->
                        httpRequests
                                .requestMatchers(HttpMethod.GET,"/api/animals").authenticated()
                                .requestMatchers("/api/animals").authenticated()
                                .requestMatchers(HttpMethod.GET,"/api/animals/**").permitAll()
                                .requestMatchers("/api/animals/**").authenticated()
                                .requestMatchers("api/users/me").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
