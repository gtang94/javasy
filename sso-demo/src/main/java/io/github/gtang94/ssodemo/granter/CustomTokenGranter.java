package io.github.gtang94.ssodemo.granter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomTokenGranter {

    // TokenGranter 令牌授予者，各授权模式对应的TokenGranter：
    // 授权码模式 -- AuthorizationCodeTokenGranter
    // 客户端模式 -- ClientCredentialsTokenGranter
    // 简单模式 -- ImplicitTokenGranter
    // 刷新token模式 -- RefreshTokenGranter
    // 密码模式 -- ResourceOwnerPasswordTokenGranter
    // 所有的TokenGranter都是基于AbstractTokenGranter的，也就是授权都是走这个抽象类的grant方法的：
    //    1. 先判断传入的grantType是否符合当前TokenGranter，不符合直接return
    //    2. 根据TokenRequest中的clientId加载ClientDetails信息
    //    3. 判断ClientDetails信息中的authorizedGrantTypes是否包含着传入的grantType
    //    5. 通过tokenService创建accessToken并返回（这一步中调用了重写的getOAuth2Authentication方法）
    public static TokenGranter getTokenGranter(AuthenticationManager authenticationManager,
                                               AuthorizationServerEndpointsConfigurer endpointsConfigurer
                                               ) {

        List<TokenGranter> granterList = new ArrayList<>(Collections.singletonList(endpointsConfigurer.getTokenGranter()));
        granterList.add(
                new SmsTokenGranter(authenticationManager, endpointsConfigurer.getTokenServices(), endpointsConfigurer.getClientDetailsService(),  endpointsConfigurer.getOAuth2RequestFactory())
        );
        return new CompositeTokenGranter(granterList);

    }
}
