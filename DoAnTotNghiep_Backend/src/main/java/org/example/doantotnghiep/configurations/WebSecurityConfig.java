package org.example.doantotnghiep.configurations;


import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.filters.JwtTokenFilter;
import org.example.doantotnghiep.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    //Pair.of(String.format("%s/products", apiPrefix), "GET"),
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/user/register", apiPrefix),
                                    String.format("%s/user/login", apiPrefix),
                                    String.format("%s/user/confirm-register", apiPrefix),
                                    String.format("%s/user/requestForgotPassword", apiPrefix),
                                    String.format("%s/user/changePassword", apiPrefix),
                                    String.format("%s/user/confirmForgotPassword", apiPrefix),
                                    String.format("%s/user/getExaminationService", apiPrefix),
                                    String.format("%s/user/getDoctorbySpecialist", apiPrefix),
                                    String.format("%s/user/getScheduleByExamName", apiPrefix),
                                    String.format("%s/user/bookingSchedule", apiPrefix),
                                    String.format("%s/user/getScheduleByDoctor", apiPrefix),
                                    String.format("%s/user/search", apiPrefix),
                                    String.format("%s/user/getAllDoctor", apiPrefix)
                            )
                            .permitAll()
                            .requestMatchers(POST, String.format("/%s/user/refreshtoken",apiPrefix)).hasAnyRole(Role.admin, Role.doctor)
                            .requestMatchers(POST, String.format("/%s/user/refreshtoken",apiPrefix)).hasAnyRole(Role.admin, Role.doctor)
                            .requestMatchers(PUT, String.format("/%s/user/changePassword",apiPrefix)).hasRole(Role.doctor)
                            .requestMatchers(PUT, String.format("/%s/user/changePassword",apiPrefix)).hasRole(Role.admin)
                            .requestMatchers(POST, String.format("/%s/user/createDoctor",apiPrefix)).hasRole(Role.admin)
                            .requestMatchers(DELETE, String.format("/%s/user/deleteDoctor",apiPrefix)).hasRole(Role.admin)
//                            .requestMatchers(GET, String.format("/%s/user/getScheduleByDoctor",apiPrefix)).hasRole(Role.doctor)
                            .requestMatchers(POST, String.format("/%s/user/addSchedule",apiPrefix)).hasRole(Role.doctor)
                            .requestMatchers(PUT, String.format("/%s/user/resetSchedule",apiPrefix)).hasRole(Role.doctor)
                            .requestMatchers(DELETE, String.format("/%s/user/deleteSchedule",apiPrefix)).hasRole(Role.doctor)
                            .requestMatchers(POST, String.format("/%s/user/addHospital",apiPrefix)).hasRole(Role.admin)
                            .requestMatchers(PUT, String.format("/%s/user/updateHospital",apiPrefix)).hasRole(Role.admin)

                            .requestMatchers(POST, String.format("/%s/user/addSpecialist",apiPrefix)).hasRole(Role.admin)
//                            .requestMatchers(GET, String.format("/%s/user/getAllDoctor",apiPrefix)).hasRole("ADMIN")
                            .anyRequest().authenticated();
                    //.anyRequest().permitAll();

                })
                .csrf(AbstractHttpConfigurer::disable);
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });

        return http.build();
    }
}
