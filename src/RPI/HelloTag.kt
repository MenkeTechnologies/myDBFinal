package RPI

import jakarta.servlet.jsp.JspException
import jakarta.servlet.jsp.tagext.SimpleTagSupport
import java.io.IOException

class HelloTag : SimpleTagSupport() {

    @Throws(IOException::class, JspException::class)
    private fun printer() {
        val jspWriter = jspContext.out
        jspWriter.print("in the printer method")
        jspBody.invoke(null)
    }

    @Throws(JspException::class, IOException::class)
    override fun doTag() {
        printer()
    }
}
