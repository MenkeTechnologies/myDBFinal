package RPI

import jakarta.servlet.http.HttpServletRequest
import java.io.IOException
import java.util.Scanner

object DB_ElectronicsCollection {
    private var errorMessage: String = ""

    @JvmStatic
    fun update(statement: java.sql.Statement, request: HttpServletRequest): String {
        val action = request.getParameter("action")

        if (action != null) {
            if (action == "clear list") {
                errorMessage = DB_Electronics.remove(-1, statement)
                return errorMessage
            }

            val name = request.getParameter("name")
            val onFilePath = request.getParameter("onFilePath")
            val offFilePath = request.getParameter("offFilePath")
            val program = request.getParameter("program")
            val dateAdded = "NOW()"

            val device = DB_Electronics(name, onFilePath, offFilePath, dateAdded)

            when (action) {
                "add" -> {
                    errorMessage = if (name.isEmpty()) "Name cannot be empty." else device.insert(statement)
                }
                "Remove" -> {
                    println("the name is $name")
                    val strIndex = request.getParameter("index")
                    val index = strIndex.toInt()
                    println("From the remove the index is $strIndex")
                    errorMessage = DB_Electronics.remove(index, statement)
                }
                "Update" -> {
                    val strIndex = request.getParameter("index")
                    val index = strIndex.toInt()
                    errorMessage = device.update(index, statement)
                }
                "TurnOn" -> {
                    val path = request.getParameter("onFilePath")
                    errorMessage = runExecutable(path)
                }
                "TurnOff" -> {
                    val path = request.getParameter("offFilePath")
                    errorMessage = runExecutable(path)
                }
            }
        }

        val electronicsCollection = ArrayList<DB_Electronics>()
        errorMessage += DB_Electronics.getDevices(statement, electronicsCollection)
        request.setAttribute("ElectronicsCollection", electronicsCollection)

        return errorMessage
    }

    private fun runExecutable(path: String): String {
        val executor = "python3"
        val pb = ProcessBuilder(executor, path, "2>", "/dev/null")
        println("in the exectutor $path")
        try {
            val p = pb.start()
            val scan = Scanner(p.inputStream)
            while (scan.hasNextLine()) {
                errorMessage = scan.nextLine()
            }
            println(errorMessage)
        } catch (e: IOException) {
            errorMessage = e.toString()
        }
        return errorMessage
    }
}
