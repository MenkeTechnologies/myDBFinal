package RPI

import jakarta.servlet.ServletException
import jakarta.servlet.annotation.MultipartConfig
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.Part
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@MultipartConfig
class Upload : HttpServlet() {

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

        val uploadedDirectory = "/home/pi/Uploads"
        val filePart = request.getPart("file")
        val fileName = getFileName(filePart)

        println("the FileName was $fileName")
        var resultString: String

        try {
            BufferedOutputStream(FileOutputStream(File("$uploadedDirectory/$fileName")), 1_000_000).use { out ->
                BufferedInputStream(filePart.inputStream, 1_000_000).use { input ->
                    val bytes = ByteArray(8192)
                    var read: Int
                    while (input.read(bytes).also { read = it } != -1) {
                        out.write(bytes, 0, read)
                    }
                }
            }
            resultString = "New File Created at $uploadedDirectory/$fileName"
        } catch (e: Exception) {
            resultString = "Did You Choose a FILE?: "
        }

        request.setAttribute("result", resultString)
        val requestDispatcher = servletContext.getRequestDispatcher("/app/upload.jsp")
        requestDispatcher.forward(request, response)
    }

    private fun getFileName(part: Part): String? {
        for (content in part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "")
            }
        }
        return null
    }
}
