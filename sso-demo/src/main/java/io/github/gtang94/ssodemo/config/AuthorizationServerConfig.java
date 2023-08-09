package io.github.gtang94.ssodemo.config;

import io.github.gtang94.ssodemo.granter.CustomTokenGranter;
import io.github.gtang94.ssodemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

// 认证服务器配置
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // https://juejin.cn/post/6846687598442708999
    // Spring Security是通过Servlet过滤器实现的。
    // SS提供了一些默认的过滤器，包括默认的负责认证的过滤器（例：UsernamePassword.../BasicAuthentication。注意，JWT认证过滤器不是默认提供）、默认的负责异常处理的过滤器、默认的负责授权的过滤器。
    // 基础认证流程：
    // 1. 请求携带身份信息到达过滤器
    // =》 2. 经过AuthenticationManager的认证
    // =》 3. 通过SecurityContextHolder获取SecurityContext
    // =》 4. 将认证后的信息放入SecurityHolder

    @Resource
    private JwtTokenStore jwtTokenStore;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private MyUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    /**
     * 配置令牌端点的访问权限和安全
     * 默认过滤器： BasicAuthenticationFilter
     *
     * 对一下的几个端点进行权限配置：
     * /oauth/authorize 授权端点
     * /oauth/token 令牌端点
     * /oauth/confirm_access 用户确认授权提交端点
     * /oauth/error 授权服务错误信息端点
     * /oauth/check_token 用于资源服务访问的令牌解析端点
     * /oauth/token_key 提供公有密钥的端点，如果使用JWT的话
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // AuthorizationServerSecurityConfigurer 是SpringSecurity提供配置端点(/oauth/****)的安全访问规则、过滤器的类

        //允许表单认证，针对/oauth/token端点
        security.allowFormAuthenticationForClients();
        // 开启/oauth/token_key验证端口无权限访问
        security.tokenKeyAccess("permitAll()");
        // 开启/oauth/check_token验证端口认证权限访问
        security.checkTokenAccess("isAuthenticated");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * 配置授权、令牌的访问端点和令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 无论哪种授权模式，在授权认证完成后，都是通过 AbstractTokenGranter 中调用 AuthorizationServerTokenServices#createAccessToken 方法颁发令牌
        // JWT中可以包含用户附加信息，在用createAccessToken颁发令牌时，使用 TokenEnhancer 向JWT添加附加信息（通过JwtAccessTokenConverter，将JWT的令牌和Oauth身份信息转换）
        // 获取自定义tokengranter
        TokenGranter tokenGranter = CustomTokenGranter.getTokenGranter(authenticationManager, endpoints);

        // 配置端点
        endpoints.tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)
                .tokenGranter(tokenGranter)
                .userDetailsService(userDetailsService);

    }


}
