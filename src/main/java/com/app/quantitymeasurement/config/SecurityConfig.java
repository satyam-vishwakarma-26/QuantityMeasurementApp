package com.app.quantitymeasurement.config;

import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())

            // 🔐 Authorization
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            )

            // 🔥 GOOGLE LOGIN + SAVE USER + JWT
            .oauth2Login(oauth -> oauth
                    .successHandler((request, response, authentication) -> {

                        String email = authentication.getName();

                        // ✅ Save user if not exists
                        User user = userRepository.findByUsername(email)
                                .orElseGet(() -> {
                                    User newUser = new User();
                                    newUser.setUsername(email);

                                    // ✅ FIX: create encoder manually (NO circular dependency)
                                    PasswordEncoder encoder = new BCryptPasswordEncoder();
                                    newUser.setPassword(encoder.encode("GOOGLE_USER"));

                                    newUser.setRole("ROLE_USER");

                                    return userRepository.save(newUser);
                                });

                        // ✅ Generate JWT
                        String token = jwtUtil.generateToken(user.getUsername());

                        // ✅ Send response
                        response.setContentType("application/json");
                        response.getWriter().write("{\"token\":\"" + token + "\"}");
                    })
            )

            // 🔐 Stateless session
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🔐 JWT Filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // H2 console fix
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // 🔥 Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 🔐 Password Encoder (used in normal login/register)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🌐 CORS Config
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:8080"
        ));

        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}