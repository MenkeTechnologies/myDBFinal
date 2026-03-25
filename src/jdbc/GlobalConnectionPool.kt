package jdbc

import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import java.sql.SQLException

class GlobalConnectionPool : ServletContextListener {

    override fun contextInitialized(sce: ServletContextEvent) {
        println("here in the global connection ppol servlet context initialzied")
        val servletContext = sce.servletContext
        val connectionPool = ConnectionPool()

        val userid = servletContext.getInitParameter("db_userid")
        val password = servletContext.getInitParameter("db_password")

        val initialConnections = 3
        val maxConnections = 20
        val waitIfBusy = true

        try {
            println("trying to create the connection pool...")
            connectionPool.createConnectionPool(userid, password, initialConnections, maxConnections, waitIfBusy)
            println("created the connection pool...")
            servletContext.setAttribute("connectionPool", connectionPool)
            println("GlobalConnectionPool has setup the connection pool")
        } catch (e: SQLException) {
            println("init SQLException caught: $e")
        }
    }

    override fun contextDestroyed(sce: ServletContextEvent) {
        val servletContext = sce.servletContext
        val connectionPool = servletContext.getAttribute("connectionPool") as? ConnectionPool
        connectionPool?.closeAllConnections()
        if (connectionPool != null) {
            println("GlobalConnectionPool closed out the connection pool")
        }
    }
}
