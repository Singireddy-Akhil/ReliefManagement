package org.cognizant.reliefmanagement.config;

import org.cognizant.reliefmanagement.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Use this instead of @EnableWebMvc for Security
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/login", "/api/users/createUser","/api/citizens/createCitizen").permitAll() // ALLOW THESE WITHOUT LOGIN
                        .requestMatchers("/api/citizens/createCitizen", "/api/citizens/update/{id}", "/api/documents/upload","/api/documents/delete/{id}", "/api/users/login","/api/reports/getreportbyid/{id}","/api/reports/createreport","/api/reports/getreportwithcitizendetails/{id}/details/","/api/programs/","/ReliefItems/getReliefItemById/{id}","/Shelters/getShelters","/Shelters/getById/{id}","/api/Distributions/deleteDistribution/{id}").hasRole("CITIZEN")
                        .requestMatchers("/api/audits/**", "/api/logs/**").hasAnyRole("AUDITOR","MANAGER")
                        .requestMatchers("/api/logs/GetAllLogs", "/api/logs/CreateLog","/api/citizens/getAllCitizens","/api/ReliefItems/**","/api/distributions/**","/api/shelters/**").hasRole("AUDITOR")
                        .requestMatchers("/api/compliance-records/**").hasRole("COMPLIANCE")
                                // Officer Endpoints
                        .requestMatchers("/api/citizens/getCitizenById/{id}", "/api/citizens/getAllCitizens", "/api/citizens/delete/{id}", "/api/documents/getDocById/{id}", "/api/incidents/**", "/api/recoveries/**","/api/reports/getreportbyid/{id}","/api/ReliefItems/**","/api/Distributions/**","/api/Shelters/**").hasRole("OFFICER")

// Manager Endpoints (Now includes all Officer endpoints)
                        .requestMatchers("/api/users/getByUserId/{id}", "/api/users/getAllUsers", "/api/users/update/{id}", "/api/users/delete/{id}",
                                        "/api/citizens/getCitizenById/{id}", "/api/citizens/getAllCitizens", "/api/citizens/delete/{id}", "/api/documents/getDocById/{id}",
                                        "/api/incidents/**", "/api/Shelters/**", "/api/recoveries/**", "/api/distributions/**","/api/ReliefItems/**").hasRole("MANAGER")
                        .anyRequest().authenticated() // LOCK EVERYTHING ELSE"
                )

                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
//        return configuration.getAuthenticationManager();
//
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return provider;
//    }


}