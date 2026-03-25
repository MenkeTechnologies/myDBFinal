package RPI

fun main() {
    print("dogs are cool in the house %05d".format(10))

    val uname = "jmenke"
    val passwordQuery = "select * from dbUsers where username = '$uname'"

    println(passwordQuery)
}
