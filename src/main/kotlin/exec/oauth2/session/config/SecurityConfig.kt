package exec.oauth2.session.config

import exec.oauth2.session.ServiceImpl.CustomOAuth2UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(private val customOAuth2UserService: CustomOAuth2UserServiceImpl) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }

        http
            .formLogin { login: FormLoginConfigurer<HttpSecurity> -> login.disable() }

        http
            .httpBasic { basic: HttpBasicConfigurer<HttpSecurity> -> basic.disable() }

        http
            .oauth2Login { oauth2: OAuth2LoginConfigurer<HttpSecurity?> ->
                oauth2
                    .userInfoEndpoint(Customizer { userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)})
            }

        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }
}