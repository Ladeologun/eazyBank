package com.xproj.eazybank.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class EazyBankUsernamepwdAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Boolean isprod = isProd();
        System.out.println(isprod);
        //for environment/profile that is prod we do not check the password, we just authenticate
        //trying to simulate easier auth for testing
        return new UsernamePasswordAuthenticationToken(username,pwd,userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isProd() {
        //this is just to show how to get the active profile
        System.out.println("-----------------------");
        System.out.println(Arrays.asList(env.getActiveProfiles()));
        System.out.println("-----------------------");
        return env.getProperty("spring.profiles.active").equals("prod");
    }
}
