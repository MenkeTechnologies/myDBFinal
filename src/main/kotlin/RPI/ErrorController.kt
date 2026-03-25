package RPI

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CustomErrorController : ErrorController {

    @GetMapping("/error404")
    fun error404(): String {
        return "error404"
    }

    @GetMapping("/error")
    fun error(): String {
        return "error404"
    }
}
