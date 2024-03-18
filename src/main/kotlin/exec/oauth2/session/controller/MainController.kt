package exec.oauth2.session.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@ResponseBody
class MainController {
    @GetMapping
    fun mainPage():String{
        return "main"
    }
}