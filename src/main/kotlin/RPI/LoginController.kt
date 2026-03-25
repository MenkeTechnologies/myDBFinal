package RPI

import jdbc.IP
import jdbc.MyConnection
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoginController {

    @GetMapping("/", "/Welcome.jsp")
    fun welcomePage(): String {
        return "Welcome"
    }

    @PostMapping("/Login")
    fun login(
        @RequestParam("uname") uname: String?,
        @RequestParam("pw") pw: String?,
        request: HttpServletRequest
    ): String {
        try {
            MyConnection.getConnection("root", "root", IP.passwd)?.use { connection ->
                val statement = connection.createStatement()
                val passwordQuery = "select * from dbUsers where username = '$uname'"
                println("query was $passwordQuery")

                val rs = statement.executeQuery(passwordQuery)

                var dbPassword = ""
                var salt = ""

                if (rs.next()) {
                    dbPassword = rs.getString("password")
                    salt = rs.getString("salt")
                }

                if (HasherSalter().hashFunction(pw + salt) == dbPassword) {
                    request.session.setAttribute("user", uname)
                    return "app/directory"
                } else {
                    request.setAttribute("error", "bad_login!")
                    return "Welcome"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        request.setAttribute("error", "bad_login!")
        return "Welcome"
    }
}
