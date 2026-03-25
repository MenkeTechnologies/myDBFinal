package RPI

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException

@WebFilter("/app/*")
class LoginFilter : Filter {

    override fun init(filterConfig: FilterConfig) {}

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val request = req as HttpServletRequest
        val response = res as HttpServletResponse
        val session = request.getSession(false)

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/db/Welcome.jsp")
        } else {
            chain.doFilter(req, res)
        }
    }

    override fun destroy() {}
}
