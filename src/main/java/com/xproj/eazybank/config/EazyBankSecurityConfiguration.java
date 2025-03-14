package com.xproj.eazybank.config;

import com.xproj.eazybank.exceptionHandlers.CustomAccessDeniedHandler;
import com.xproj.eazybank.exceptionHandlers.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@Profile("!prod")
@EnableMethodSecurity
public class EazyBankSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.
            requiresChannel(rcc->rcc.anyRequest().requiresInsecure()).
            csrf((csrf) -> csrf.disable()).
            authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/contact", "/notices", "/error", "/register").permitAll()
                    .anyRequest().authenticated();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(hbc-> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(hge->hge.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker passwordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }


}
