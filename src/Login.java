import java.util.Scanner;

/**
 * Created by Gabriel on 2016-11-09.
 */
public class Login {
    public static boolean authenticate(String[] credentials){

        boolean credentials_OK = false;

        if (credentials[0].equals("root") && credentials[1].equals("admin")){
            credentials_OK = true;
        }

        return credentials_OK;
    }
}