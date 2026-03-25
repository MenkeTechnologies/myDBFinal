package RPI

import jdbc.IP
import jdbc.MyConnection
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ElectronicsController {

    @GetMapping("/app/DB_ElectronicsServlet")
    fun showElectronics(request: HttpServletRequest): String {
        return processRequest(request)
    }

    @PostMapping("/app/DB_ElectronicsServlet")
    fun handleElectronics(request: HttpServletRequest): String {
        return processRequest(request)
    }

    private fun processRequest(request: HttpServletRequest): String {
        var errorMessage = ""
        try {
            val connection = MyConnection.getConnection("root", "root", IP.passwd)
            val statement = connection!!.createStatement()
            errorMessage = DB_ElectronicsCollection.update(statement, request)
            statement.close()
        } catch (e: Exception) {
            errorMessage = e.toString()
            println(e.cause)
        }

        request.setAttribute("errorMessage", errorMessage)
        return "app/ElectronicsCollection"
    }
}
