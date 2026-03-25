package RPI

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Base64

class HasherSalter {

    @Throws(NoSuchAlgorithmException::class)
    fun hashFunction(toHash: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.reset()
        md.update(toHash.toByteArray())

        val bytes = md.digest()
        val sb = StringBuilder(bytes.size * 2)

        for (b in bytes) {
            val v = b.toInt() and 0xFF
            if (v < 16) sb.append('0')
            sb.append(Integer.toHexString(v))
        }

        return sb.toString()
    }

    fun getSalt(): String {
        val myRand = SecureRandom()
        val saltBytes = ByteArray(32)
        myRand.nextBytes(saltBytes)
        return Base64.getEncoder().encodeToString(saltBytes)
    }
}

fun main() {
    val mystring = "231235dogs"

    val md = MessageDigest.getInstance("SHA-256")
    md.reset()
    md.update(mystring.toByteArray())
    val secondbytes = md.digest()

    println(secondbytes.size)
}
