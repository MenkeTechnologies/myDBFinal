package RPI

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DirectoryController {

    @GetMapping("/app/directory.jsp")
    fun showDirectory(): String {
        return "app/directory"
    }
}
