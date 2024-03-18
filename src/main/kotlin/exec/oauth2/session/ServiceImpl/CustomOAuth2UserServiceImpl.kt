package exec.oauth2.session.ServiceImpl

import exec.oauth2.session.dto.response.CustomOAuth2User
import exec.oauth2.session.dto.response.NaverResponse
import exec.oauth2.session.dto.response.OAuth2Response
import exec.oauth2.session.service.CustomOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserServiceImpl: DefaultOAuth2UserService(), CustomOAuth2UserService {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val oAuth2User:OAuth2User = super.loadUser(userRequest)
        print(oAuth2User.attributes)

        val registrationId = userRequest?.clientRegistration!!.registrationId
        val oAuth2Response: OAuth2Response
        if (registrationId == "naver"){
            oAuth2Response = NaverResponse(oAuth2User.attributes)
        }

        else{
            return oAuth2User
        }

        val role:String = "ROLE_USER"
        return CustomOAuth2User(oAuth2Response, role)
    }
}