package RPI;

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

        StringBuilder sb = new StringBuilder(bytes.length*2);
        for (byte aByte : bytes) {
            int v = aByte & 0xFF;
            if (v<16) sb.append('0');
            sb.append(Integer.toHexString(v));

        }

        return sb.toString();

    }

    String getSalt(){
        Random myRand = new SecureRandom();
        byte[] saltbyes = new byte[32];
        myRand.nextBytes(saltbyes);
        return Base64.getEncoder().encodeToString(saltbyes);

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {


    }


}
