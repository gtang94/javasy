package io.github.gtang94.ssodemo.granter;

import io.github.gtang94.ssodemo.SmsAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Map;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
public class SmsTokenGranter extends AbstractTokenGranter {

    public static final String GRANT_TYPE = "sms";

    private final AuthenticationManager authenticationManager;

//	private IUserService userService;
//	private RedisUtil redisUtil;

    protected SmsTokenGranter(AuthenticationManager authenticationManager,
                              AuthorizationServerTokenServices tokenServices,
                              ClientDetailsService clientDetailsService,
                              OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

//	@Override
//	public UserInfo grant(TokenParameter tokenParameter) {
//		HttpServletRequest request = WebUtil.getRequest();
//
//		String key = request.getHeader(TokenUtil.CAPTCHA_HEADER_KEY);
//		String code = request.getHeader(TokenUtil.CAPTCHA_HEADER_CODE);
//		// 获取验证码
//		String redisCode = String.valueOf(redisUtil.get(CacheNames.CAPTCHA_KEY + key));
//		// 判断验证码
//		if (code == null || !StringUtil.equalsIgnoreCase(redisCode, code)) {
//			throw new ServiceException(TokenUtil.CAPTCHA_NOT_CORRECT);
//		}
//
//		String tenantId = tokenParameter.getArgs().getStr("tenantId");
//		String account = tokenParameter.getArgs().getStr("account");
//		String password = tokenParameter.getArgs().getStr("password");
//		UserInfo userInfo = null;
//		if (Func.isNoneBlank(account, password)) {
//			// 获取用户类型
//			String userType = tokenParameter.getArgs().getStr("userType");
//			// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
//			if (userType.equals(BladeUserEnum.WEB.getName())) {
//				userInfo = userService.userInfo(tenantId, account, DigestUtil.encrypt(password));
//			} else if (userType.equals(BladeUserEnum.APP.getName())) {
//				userInfo = userService.userInfo(tenantId, account, DigestUtil.encrypt(password));
//			} else {
//				userInfo = userService.userInfo(tenantId, account, DigestUtil.encrypt(password));
//			}
//		}
//		return userInfo;
//	}

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = tokenRequest.getRequestParameters();
        String phone = parameters.get("phone");
		String smsCode = parameters.get("smsCode");
		if (!checkSmsCode(phone, smsCode)) {
			throw new UserDeniedAuthorizationException("sms code error");
		}

        Authentication authenticationToken = new SmsAuthenticationToken(phone, phone);
        ((AbstractAuthenticationToken) authenticationToken).setDetails(parameters);
        try {
            authenticationToken = authenticationManager.authenticate(authenticationToken);
        } catch (AccountStatusException | BadCredentialsException ase) {
            throw new InvalidGrantException(ase.getMessage());
        }


        if (authenticationToken == null || !authenticationToken.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + phone);
        }
		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		return new OAuth2Authentication(storedOAuth2Request, authenticationToken);

//		HttpServletRequest request = WebUtil.getRequest();
//
//		String key = request.getHeader(TokenUtil.CAPTCHA_HEADER_KEY);
//		String code = request.getHeader(TokenUtil.CAPTCHA_HEADER_CODE);
//		// 获取验证码
//		String redisCode = String.valueOf(redisUtil.get(CacheNames.CAPTCHA_KEY + key));
//		// 判断验证码
//		if (code == null || !StringUtil.equalsIgnoreCase(redisCode, code)) {
//			throw new ServiceException(TokenUtil.CAPTCHA_NOT_CORRECT);
//		}
//		redisUtils.del(CacheNames.CAPTCHA_KEY + key);

//		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
//		String username = parameters.get("username");
//		String cryptPassword = parameters.get("password");
//
//		String privateKey = redisUtils.get("xxxx:rsa-privateKey" + preSessionId);
//		byte[] decrypt = AesUtil.decrypt(Base64Util.decodeFromString(cryptPassword),
//				RsaUtil.decrypt(privateKey, Base64Util.decodeFromString(authKey)),
//				RsaUtil.decrypt(privateKey, Base64Util.decodeFromString(authIv)));
//		String md5Password = new String(decrypt);
//		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, md5Password);
//		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
//		try {
//			userAuth = authenticationManager.authenticate(userAuth);
//		}
//		catch (AccountStatusException | BadCredentialsException ase) {
//			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
//			throw new InvalidGrantException(ase.getMessage());
//		}
//		// If the username/password are wrong the spec says we should send 400/invalid grant
//
//		if (userAuth == null || !userAuth.isAuthenticated()) {
//			throw new InvalidGrantException("Could not authenticate user: " + username);
//		}
//
//		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
//		return new OAuth2Authentication(storedOAuth2Request, userAuth);
//        return null;
    }

	private boolean checkSmsCode(String phone, String smsCode) {
		return true;
	}

}
