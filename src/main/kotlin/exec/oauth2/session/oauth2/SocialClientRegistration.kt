package exec.oauth2.session.oauth2

import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.stereotype.Component

@Component
class SocialClientRegistration {
    fun naverClientRegistration():ClientRegistration {
        return ClientRegistration.withRegistrationId("naver")
            .clientId("아이디")
            .clientSecret("비밀번호")
            .redirectUri("http://localhost:8080/login/auth2/code/naver")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .tokenUri("https://nid.naver.com/oauth2.0/authorize")
            .userInfoUri("https://openapi.naver.com/v1/nid/me")
            .userNameAttributeName("response")
            .build()
    }
}