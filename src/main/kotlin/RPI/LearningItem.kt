package RPI

import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class LearningItem(
    var category: String,
    var learning: String,
    var dateAdded: String,
    var index: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as LearningItem
        return index == other.index &&
                category == other.category &&
                learning == other.learning &&
                dateAdded == other.dateAdded
    }

    override fun hashCode(): Int {
        var result = category.hashCode()
        result = 31 * result + learning.hashCode()
        result = 31 * result + dateAdded.hashCode()
        result = 31 * result + index
        return result
    }

    override fun toString(): String =
        "LearningItem(category='$category', learning='$learning', dateAdded='$dateAdded', index=$index)"

    fun update(index: Int, statement: Statement): String {
        if (learning.isEmpty()) return "Learning Item cannot be empty"

        val sql = "update $DB_NAME set category=${qSurround(category)}" +
                ", learning=${qSurround(learning)}where id=$index"
        println("the update sql: $sql")
        return executeUpdate(sql, statement)
    }

    fun insert(statement: Statement): String {
        var sql = "select * from $DB_NAME where category=${qSurround(category)}" +
                " AND learning=${qSurround(learning)}"
        println(sql)
        try {
            val rs = statement.executeQuery(sql)
            if (rs.next()) return "Learning Item already exists"
        } catch (e: SQLException) {
            return e.toString()
        }

        sql = "insert into $DB_NAME values(${qSurround(category)},${qSurround(learning)},NOW(), null)"
        return executeUpdate(sql, statement)
    }

    private fun qSurround(s: String): String = "'$s'"

    companion object {
        private const val DB_NAME = "LearningCollection"

        @JvmStatic
        fun remove(index: Int, statement: Statement): String {
            var sql = "delete from $DB_NAME"
            if (index >= 0) sql += " where id=$index"
            return executeUpdate(sql, statement)
        }

        private fun executeUpdate(sql: String, statement: Statement): String {
            var error = ""
            try {
                println("sql=$sql")
                statement.executeUpdate(sql)
            } catch (e: SQLException) {
                error = e.toString()
            }
            return error
        }

        @JvmStatic
        fun getLearningItems(statement: Statement, learningItemsArrayList: ArrayList<LearningItem>): String {
            var error = ""
            try {
                val sql = "select * from $DB_NAME"
                println("sql=$sql")
                val rs = statement.executeQuery(sql)
                learningItemsArrayList.clear()
                while (rs.next()) {
                    val category = rs.getString("category")
                    val learningString = rs.getString("learning")
                    val dateAdded = rs.getString("dateAdded")
                    val ind = rs.getInt("id")
                    learningItemsArrayList.add(LearningItem(category, learningString, dateAdded, ind))
                }
            } catch (ex: SQLException) {
                error = ex.toString()
            }
            return error
        }
    }
}
