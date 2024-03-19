package exec.oauth2.session.config

import exec.oauth2.session.ServiceImpl.CustomOAuth2UserServiceImpl
import exec.oauth2.session.oauth2.CustomClientRegistrationRepo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private var customOAuth2UserService: CustomOAuth2UserServiceImpl,
    private var customClientRegistrationRepo:CustomClientRegistrationRepo
) {
    fun SecurityConfig(customOAuth2UserService: CustomOAuth2UserServiceImpl, customClientRegistrationRepo: CustomClientRegistrationRepo){
        this.customOAuth2UserService = customOAuth2UserService
        this.customClientRegistrationRepo = customClientRegistrationRepo
    }
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }

        http
            .formLogin { login -> login.disable() }

        http
            .httpBasic { basic -> basic.disable() }

        http
            .oauth2Login { oauth2 ->
                oauth2
                    .clientRegistrationRepository(customClientRegistrationRepo.clientRegistrationRepository())
                    .userInfoEndpoint { userInfoEndpointConfig ->
                        userInfoEndpointConfig.userService(
                            customOAuth2UserService
                        )
                    }
            }

        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }
}