package RPI

import jdbc.IP
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import jakarta.annotation.PostConstruct

@SpringBootApplication
class MyDBApplication {

    @Value("\${db.host}")
    private lateinit var dbHost: String

    @Value("\${db.password}")
    private lateinit var dbPassword: String

    @Value("\${db.port}")
    private lateinit var dbPort: String

    @PostConstruct
    fun initDatabase() {
        IP.ipAddr = dbHost
        IP.passwd = dbPassword
        IP.port = dbPort
    }
}

fun main(args: Array<String>) {
    runApplication<MyDBApplication>(*args)
}
