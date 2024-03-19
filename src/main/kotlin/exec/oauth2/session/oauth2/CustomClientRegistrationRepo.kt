package exec.oauth2.session.oauth2

import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository

@Configuration
class CustomClientRegistrationRepo(
    private var socialClientRegistration: SocialClientRegistration
) {
    fun CustomClientRegistrationRepo(socialClientRegistration: SocialClientRegistration){
        this.socialClientRegistration = socialClientRegistration
    }

//    fun clientRegistrationRepository(): ClientRegistrationRepository
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(
            socialClientRegistration.naverClientRegistration()
        )
    }
}