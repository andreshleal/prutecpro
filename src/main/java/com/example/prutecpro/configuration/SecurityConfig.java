package com.example.prutecpro.configuration;


import com.example.prutecpro.security.JwtAuthenticationEntryPoint;
import com.example.prutecpro.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final static String ADMIN = "ADMIN";
    private final static String ROLE_ADMIN = "ROLE_ADMIN";
    private final static String USER = "USER";

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        return source;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/pc/**").permitAll()
                .antMatchers(HttpMethod.POST,"/encuesta").hasAnyRole(USER,ADMIN)
//                .antMatchers(HttpMethod.PUT,"/encuesta/*").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE,"/encuesta/*").hasAnyRole(USER,ADMIN)






                .antMatchers(HttpMethod.POST,"/api/auth/registro").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").hasAnyRole(USER,ADMIN)
                .antMatchers(HttpMethod.GET,"/api/auth/usuarios").hasAuthority(ROLE_ADMIN)

                .anyRequest().authenticated();
                    http.addFilterBefore(jwtAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
