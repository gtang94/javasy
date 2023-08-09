package io.github.gtang94.ssodemo.provider;

import io.github.gtang94.ssodemo.SmsAuthenticationToken;
import io.github.gtang94.ssodemo.service.MyUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;

@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private MyUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone = authentication.getCredentials() == null ? null : authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("no user");
        }
        SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(authentication.getCredentials(), userDetails, new ArrayList<>());
        smsAuthenticationToken.setDetails(authentication.getDetails());
        return smsAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
