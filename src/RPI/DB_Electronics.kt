package RPI

import java.sql.SQLException
import java.sql.Statement

class DB_Electronics(
    var name: String,
    var onFilePath: String,
    var offFilePath: String,
    var dateAdded: String,
    var index: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as DB_Electronics
        return index == other.index &&
                name == other.name &&
                onFilePath == other.onFilePath &&
                offFilePath == other.offFilePath &&
                dateAdded == other.dateAdded
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + onFilePath.hashCode()
        result = 31 * result + offFilePath.hashCode()
        result = 31 * result + dateAdded.hashCode()
        result = 31 * result + index
        return result
    }

    override fun toString(): String =
        "DB_Electronics(name='$name', onFilePath='$onFilePath', offFilePath='$offFilePath', dateAdded='$dateAdded', index=$index)"

    fun update(index: Int, statement: Statement): String {
        if (name.isEmpty()) return "Name cannot be empty"

        val sql = "update ElectronicsCollection set name=${qSurround(name)}" +
                ", onFile=${qSurround(onFilePath)}, offFile=${qSurround(offFilePath)}" +
                " where id=$index"
        return executeUpdate(sql, statement)
    }

    fun insert(statement: Statement): String {
        var sql = "select * from ElectronicsCollection where name=${qSurround(name)}" +
                " AND onFile=${qSurround(onFilePath)} AND offFile=${qSurround(offFilePath)}"
        try {
            val rs = statement.executeQuery(sql)
            if (rs.next()) return "Device already exists"
        } catch (e: SQLException) {
            return e.toString()
        }

        sql = "insert into ElectronicsCollection values(${qSurround(name)}," +
                "${qSurround(onFilePath)},${qSurround(offFilePath)}, NOW(), null)"
        return executeUpdate(sql, statement)
    }

    private fun qSurround(s: String): String = "'$s'"

    companion object {
        @JvmStatic
        fun remove(index: Int, statement: Statement): String {
            var sql = "delete from ElectronicsCollection "
            if (index >= 0) sql += " where id=$index"
            println("clearList $sql")
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
        fun getDevices(statement: Statement, electronicDevices: ArrayList<DB_Electronics>): String {
            var error = ""
            try {
                val sql = "select * from ElectronicsCollection"
                println("sql=$sql")
                val rs = statement.executeQuery(sql)
                electronicDevices.clear()
                while (rs.next()) {
                    val name = rs.getString("name")
                    val onFilePath = rs.getString("onFile")
                    val offFilePath = rs.getString("offFile")
                    val dateAdded = rs.getString("dateAdded")
                    val ind = rs.getInt("id")
                    electronicDevices.add(DB_Electronics(name, onFilePath, offFilePath, dateAdded, ind))
                }
            } catch (ex: SQLException) {
                error = ex.toString()
            }
            return error
        }
    }
}
