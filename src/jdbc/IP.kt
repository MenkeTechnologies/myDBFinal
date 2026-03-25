package jdbc

class IP {
    companion object {
        @JvmStatic
        var ipAddr: String = ""

        @JvmStatic
        @get:JvmName("getPasswd_")
        @set:JvmName("setPasswd_")
        var passwd: String = ""

        @JvmStatic
        @get:JvmName("getPort_")
        @set:JvmName("setPort_")
        var port: String = "3306"
    }

    fun getIp(): String = ipAddr
    fun setIp(ip: String) { ipAddr = ip }
    fun getPasswd(): String = passwd
    fun setPasswd(passwd: String) { IP.passwd = passwd }
    fun getPort(): String = port
    fun setPort(port: String) { IP.port = port }
}
