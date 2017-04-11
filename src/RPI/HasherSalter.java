package RPI;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * Created by jacobmenke on 1/22/17.
 */
public class HasherSalter {
    String hashFunction(String toHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(toHash.getBytes());

        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            int v = aByte & 0xFF;
            if (v < 16) sb.append('0');
            sb.append(Integer.toHexString(v));
        }

        return sb.toString();
    }

    String getSalt() {
        Random myRand = new SecureRandom();
        byte[] saltbyes = new byte[32];
        myRand.nextBytes(saltbyes);

        return Base64.getEncoder().encodeToString(saltbyes);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String mystring = "231235dogs";

//
        byte[] secondbytes = {};

//
//        System.out.println(mystring.toCharArray());

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(mystring.getBytes());
        //compute the hash
        secondbytes = md.digest();

        System.out.println(secondbytes.length);


//
//        for (int i = 0; i < secondbytes.length; i++) {
//            System.out.println(secondbytes[i]);
//        }
//
//        StringBuilder sb = new StringBuilder(secondbytes.length * 2);
//
//        for (byte secondbyte : secondbytes) {
////            System.out.println("before convert to int " + secondbyte);
////            System.out.println("before anding with 1111 1111 " + (int)secondbyte);
////            System.out.println(Integer.toBinaryString(secondbyte));
//
//            System.out.println("byte was " + secondbyte);
//            //converts to positive between 0 and 255, getting last 8 bits, and with 0, 24 first bits
//            int v = secondbyte & 0xFF;
////
////            System.out.println("after anding with 1111 1111 " + v);
////            System.out.println(Integer.toBinaryString(v));
//            if (v < 16) {
//                //this is basically to maintain 2 spots for every char
//                sb.append('0');
//                System.out.println(v + " is less than 16");
//                System.out.println("after hex" + Integer.toHexString(v));
//            } else {
//
//                System.out.println(v + " greater than or equal than 16");
//                System.out.println("after hex " + Integer.toHexString(v));
//
//            }
//            System.out.println("_______");
//            sb.append(Integer.toHexString(v));
//        }
//
//
//        System.out.println(sb.toString());
//
//        System.out.println(sb.toString());
//
//        System.out.println(Base64.getEncoder().encodeToString(secondbytes));

        //
//        Random myrandy = new SecureRandom();
//
//        char x = 'A';
//
//        System.out.println((byte)x);
//
//        System.out.println(mystring.getBytes().length);

//        byte[] bytes = {-50, 66, 67};

//        System.out.println("standard utf 8 encoding");
//        System.out.println(new String(bytes, Charset.defaultCharset()));
//        System.out.println("base64 now");
//
//        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = (byte) myrandy.nextInt(128);
//        }
//
//        System.out.println(new String(bytes));

//        System.out.println(Base64.getEncoder().encodeToString(bytes));
//
//        System.out.println(Charset.defaultCharset());
//
//        myrandy.nextBytes(bytes);
//
//        System.out.println("bytes array rand");
//       for (int i = 0; i < bytes.length; i++) {
//           System.out.println(bytes[i]);
//       }
//
//       System.out.println("after encoidng");
//
//       System.out.println(Base64.getEncoder().encodeToString(bytes));

//        char myChar = (char)bytes[0];
//
//        System.out.println(myChar);

    }
}
