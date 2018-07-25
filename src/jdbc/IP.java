package jdbc;


/**
 * Created by jacobmenke on 7/14/17.
 */
public class IP {
    public static  String IPADDR = "";
    public static String passwd = "";
    public static String port = "3306";

    public static String getIP() {
        return IPADDR;
    }

    public static void setIP(String ip) {
        IP.IPADDR = ip;
    }

    public String getPasswd() {
        return passwd;
    }

    public static void setPasswd(String passwd) {
        IP.passwd = passwd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        IP.port = port;
    }
}
