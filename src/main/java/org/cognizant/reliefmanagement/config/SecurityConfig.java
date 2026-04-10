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

                                // Officer Endpoints
                        .requestMatchers("/api/citizens/getCitizenById/{id}", "/api/citizens/getAllCitizens", "/api/citizens/delete/{id}", "/api/documents/getDocById/{id}", "/api/incidents/**",  "/api/recoveries/**", "/Distributions/createDistribution","/Distributions/deleteDistribution/{id}","/Distributions/updateDistribution/{id}","/api/reports/getreportbyid/{id}","/ReliefItems/createReliefItem","/ReliefItems/updateReliefItem/{id}","/ReliefItems/deleteReliefItem/{id}","/api/shelters/createShelter","/api/shelters/updateShelter/{id}","/api/shelters/deleteShelter/{id}").hasRole("OFFICER")
                                .requestMatchers("/Distributions/getDistribution","/Distributions/getDistributionById/{id}","/ReliefItems/getReliefItem","/ReliefItems/getReliefItemById/{id}","/api/shelters/getShelters","/api/shelters/getById/{id}").hasAnyRole("OFFICER","MANAGER","CITIZEN","COMPLIANCE")
                                .requestMatchers("/ReliefItems/getReliefItem","/ReliefItems/getReliefItemById/{id}","/api/shelters/getShelters","/api/shelters/getById/{id}").hasRole("MANAGER")


                                 // LOCK EVERYTHING ELSE
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


    /*
   // @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();

    }

   // @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
*/

}