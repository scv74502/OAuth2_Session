package exec.oauth2.session.controller

import org.springframework.stereotype.Controller

@Controller
class LoginController {
    fun loginPage():String {
        return "login"
    }
}