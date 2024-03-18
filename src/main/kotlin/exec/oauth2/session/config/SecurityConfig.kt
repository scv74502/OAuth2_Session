package exec.oauth2.session.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain {
        // csrf disable
        http
            .csrf{csrf -> csrf.disable()}

        // security basic login disable, since using OAuth2
        http
            .formLogin { login -> login.disable() }

        http
            .httpBasic{ basic -> basic.disable() }

        http
            .authorizeHttpRequests{auth -> auth
                .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                .anyRequest().authenticated()}

        return http.build()
    }
}