package RPI

import jakarta.servlet.http.HttpServletRequest
import java.sql.Statement
import java.util.Collections

object DB_LearningCollection {
    private var errorMessage: String = ""

    @JvmStatic
    fun update(statement: Statement, request: HttpServletRequest): String {
        val action = request.getParameter("action")

        if (action != null) {
            if (action == "clear list") {
                errorMessage = LearningItem.remove(-1, statement)
                return errorMessage
            }

            val category = request.getParameter("category")
            val unescapedLearning = request.getParameter("learning")
            val learning = unescapedLearning.replace("'", "\\'")

            println("I am here and the the learning param is $learning")

            val dateAdded = "NOW()"
            val learningItem = LearningItem(category, learning, dateAdded)

            when (action) {
                "add" -> {
                    errorMessage = if (learning.isEmpty()) "Learning Item cannot be empty." else learningItem.insert(statement)
                }
                "Remove" -> {
                    println("the category is $category")
                    val strIndex = request.getParameter("index")
                    val index = strIndex.toInt()
                    println("From the remove the index is $strIndex")
                    errorMessage = LearningItem.remove(index, statement)
                }
                "Update" -> {
                    val strIndex = request.getParameter("index")
                    val index = strIndex.toInt()
                    errorMessage = learningItem.update(index, statement)
                }
            }
        }

        val learningCollection = ArrayList<LearningItem>()
        errorMessage += LearningItem.getLearningItems(statement, learningCollection)

        if (action != null && action == "updateAll") {
            // TODO use jquery to submit the form and action=updateAll
        }

        Collections.reverse(learningCollection)

        request.setAttribute("count", learningCollection.size)
        request.setAttribute("LearningCollection", learningCollection)

        return errorMessage
    }
}
