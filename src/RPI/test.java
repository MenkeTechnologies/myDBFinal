package RPI;

/**
 * Created by jacobmenke on 7/12/17.
 */
public class test {
    public static void main(String[] args) {

        System.out.printf("dogs are cool in the house %05d", 10);


        String uname = "jmenke";


        String passwordQuery = String.format("select * from dbUsers where username = '%s'", uname);

        System.out.println(passwordQuery);
    }


}
