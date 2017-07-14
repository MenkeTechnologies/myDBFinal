package jdbc;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by jacobmenke on 7/14/17.
 */
public class IP {
    public static String IP = "";
    public static String passwd = "";

    public String getIP() {
        return IP;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setIP(String IP) {
        jdbc.IP.IP = IP;
    }
    public void setPasswd(String passwd) {
       jdbc.IP.passwd = passwd;
    }
}
