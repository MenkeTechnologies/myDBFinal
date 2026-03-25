package jdbc

import java.sql.Connection
import java.sql.SQLException
import java.util.Vector

class ConnectionPool : Runnable {
    private var username: String = ""
    private var password: String = ""
    private var maxConnections: Int = 0
    private var waitIfBusy: Boolean = false
    private var availableConnections: Vector<Connection> = Vector()
    private var busyConnections: Vector<Connection> = Vector()
    private var connectionPending: Boolean = false
    private var initializedFlag: Boolean = false

    val isInitialized: Boolean get() = initializedFlag

    @Synchronized
    @Throws(SQLException::class)
    fun createConnectionPool(
        username: String, password: String,
        initialConnections: Int, maxConnections: Int,
        waitIfBusy: Boolean
    ) {
        if (initializedFlag) return

        this.username = username
        this.password = password
        this.maxConnections = maxConnections
        this.waitIfBusy = waitIfBusy
        val initConns = initialConnections.coerceAtMost(maxConnections)
        availableConnections = Vector(initConns)
        busyConnections = Vector()
        repeat(initConns) {
            availableConnections.addElement(makeNewConnection())
        }
        initializedFlag = true
    }

    @Synchronized
    @Throws(SQLException::class)
    fun getConnection(): Connection {
        if (availableConnections.isNotEmpty()) {
            val lastIndex = availableConnections.size - 1
            val existingConnection = availableConnections[lastIndex]
            availableConnections.removeElementAt(lastIndex)

            if (existingConnection.isClosed) {
                (this as Object).notifyAll()
                return getConnection()
            } else {
                busyConnections.addElement(existingConnection)
                return existingConnection
            }
        } else {
            if (totalConnections() < maxConnections && !connectionPending) {
                makeBackgroundConnection()
            } else if (!waitIfBusy) {
                throw SQLException("Connection limit reached")
            }

            try {
                (this as Object).wait()
            } catch (_: InterruptedException) {
            }

            return getConnection()
        }
    }

    private fun makeBackgroundConnection() {
        connectionPending = true
        try {
            val connectThread = Thread(this)
            connectThread.start()
        } catch (_: OutOfMemoryError) {
        }
    }

    override fun run() {
        try {
            val connection = makeNewConnection()
            synchronized(this) {
                availableConnections.addElement(connection)
                connectionPending = false
                (this as Object).notifyAll()
            }
        } catch (_: Exception) {
        }
    }

    @Throws(SQLException::class)
    private fun makeNewConnection(): Connection {
        return MyConnection.getConnection(username, password)
            ?: throw SQLException("Unable to create a Connection")
    }

    @Synchronized
    fun free(connection: Connection) {
        busyConnections.removeElement(connection)
        availableConnections.addElement(connection)
        (this as Object).notifyAll()
    }

    @Synchronized
    fun totalConnections(): Int = availableConnections.size + busyConnections.size

    @Synchronized
    fun closeAllConnections() {
        closeConnections(availableConnections)
        availableConnections = Vector()
        closeConnections(busyConnections)
        busyConnections = Vector()
    }

    private fun closeConnections(connections: Vector<Connection>) {
        try {
            for (connection in connections) {
                if (!connection.isClosed) {
                    connection.close()
                }
            }
        } catch (_: SQLException) {
        }
    }

    @Synchronized
    override fun toString(): String =
        "ConnectionPool($username), available=${availableConnections.size}, busy=${busyConnections.size}, max=$maxConnections"
}
