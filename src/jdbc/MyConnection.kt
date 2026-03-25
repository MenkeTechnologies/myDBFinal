package jdbc

import java.sql.Connection
import java.sql.DriverManager

object MyConnection {
    @JvmStatic
    fun getConnection(vararg args: String): Connection? {
        return try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            val url = "jdbc:mysql://${IP.ipAddr}:${IP.port}/${args[0]}?user=${args[1]}&password=${args[2]}"
            println(url)
            DriverManager.getConnection(url)
        } catch (e: Exception) {
            System.err.println(e)
            null
        }
    }
}
