package io.github.gtang94.ssodemo.granter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomTokenGranter {

    public static TokenGranter getTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerEndpointsConfigurer endpointsConfigurer
                                               ) {
        List<TokenGranter> granterList = new ArrayList<>(Collections.singletonList(endpointsConfigurer.getTokenGranter()));
//        granterList.add(
//                new CaptchaTokenGranter()
//        );
        return new CompositeTokenGranter(granterList);

    }
}
