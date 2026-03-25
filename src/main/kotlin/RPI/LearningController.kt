package RPI

import jdbc.IP
import jdbc.MyConnection
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class LearningController {

    @GetMapping("/app/DB_LearningServlet")
    fun showLearning(request: HttpServletRequest): String {
        return processRequest(request)
    }

    @PostMapping("/app/DB_LearningServlet")
    fun handleLearning(request: HttpServletRequest): String {
        return processRequest(request)
    }

    private fun processRequest(request: HttpServletRequest): String {
        var errorMessage = ""
        try {
            val connection = MyConnection.getConnection("root", "root", IP.passwd)
            val statement = connection!!.createStatement()
            errorMessage = DB_LearningCollection.update(statement, request)
            statement.close()
        } catch (e: Exception) {
            errorMessage = e.toString()
            println(e.cause)
        }

        request.setAttribute("errorMessage", errorMessage)
        return "app/LearningCollection"
    }
}
