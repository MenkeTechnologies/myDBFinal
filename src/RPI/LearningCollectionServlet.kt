package RPI

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jdbc.IP
import jdbc.MyConnection
import java.io.IOException

class LearningCollectionServlet : HttpServlet() {

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
        response.writer.use {
            var errorMessage = ""
            try {
                val connection = MyConnection.getConnection("root", "root", IP().getPasswd())
                val statement = connection!!.createStatement()

                if (statement != null) {
                    errorMessage = DB_LearningCollection.update(statement, request)
                    if (errorMessage.isNotEmpty()) {
                        println("in the inner servlet error is $errorMessage")
                    }
                    statement.close()
                }
            } catch (e: Exception) {
                errorMessage = e.toString()
                println(e.cause)
            }

            if (errorMessage.isNotEmpty()) {
                println("in the servlet error is $errorMessage")
            }

            request.setAttribute("errorMessage", errorMessage)
            val dispatcher = servletContext.getRequestDispatcher("/app/LearningCollection.jsp")
            dispatcher.forward(request, response)
        }
    }
}
