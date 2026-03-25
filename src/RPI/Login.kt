package RPI

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jdbc.IP
import jdbc.MyConnection
import java.io.FileInputStream
import java.io.IOException
import java.util.Scanner

class Login : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        processRequest(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        processRequest(request, response)
    }

    override fun getServletInfo(): String = "Short description"

    @Throws(ServletException::class, IOException::class)
    private fun processRequest(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html;charset=UTF-8"

        try {
            val scanner = Scanner(FileInputStream(servletContext.getRealPath("WEB-INF") + "/ip.txt"))
            val ip = scanner.next()
            val passwd = scanner.next()
            System.err.println(
                "__________Class:${Thread.currentThread().stackTrace[1].className}" +
                        "____Line:${Thread.currentThread().stackTrace[1].lineNumber}___________ $ip"
            )
            IP().setIp(ip)
            IP().setPasswd(passwd)
        } catch (e: Exception) {
            System.err.println(
                "__________Class:${Thread.currentThread().stackTrace[1].className}" +
                        "____Line:${Thread.currentThread().stackTrace[1].lineNumber}___________ $e"
            )
        }

        val uname = request.getParameter("uname")
        val pw = request.getParameter("pw")

        try {
            MyConnection.getConnection("root", "root", IP().getPasswd())?.use { connection ->
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
                    val dispatcher = servletContext.getRequestDispatcher("/app/directory.jsp")
                    dispatcher.forward(request, response)
                } else {
                    val dispatcher = servletContext.getRequestDispatcher("/Welcome.jsp")
                    request.setAttribute("error", "bad_login!")
                    dispatcher.forward(request, response)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
