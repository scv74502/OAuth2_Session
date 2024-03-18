package exec.oauth2.session.dto.response

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User


class CustomOAuth2User(
    private val oAuth2Response:OAuth2Response,
    private val role: String
):OAuth2User{
    override fun getAttributes(): MutableMap<String, Any>? {
        return null
    }

//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        val collection:MutableCollection<GrantedAuthority> = ArrayList()
//        collection.add(GrantedAuthority() {
//        })
//    }
    override fun getAuthorities(): MutableCollection<out GrantedAuthority?>? {
        val collection: MutableCollection<GrantedAuthority> = ArrayList()

        collection.add(GrantedAuthority { role })

        return collection
    }

    override fun getName() = oAuth2Response.getName()

    fun getUserName() = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId()
}