import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Utilities {

    // INPUTS
    public static String input(String message){
        System.out.print(message);
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine();

        return input;
    }

    public static String hiddenInput(String message){
        System.out.printf(message);
        char[] temp_password = System.console().readPassword();
        String input = String.valueOf(temp_password);

        return input;
    }

    public static int intInput(String message){
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        return number;
    }

    // ------

    public static boolean isInRange(int number, int min, int max){
        boolean isInRange = false;
        if (number >= min && number <= max){
            isInRange = true;
        }

        return isInRange;
    }

    public static boolean isInRange(int number, int min, int max, int[] exception){
        boolean isInRange = false;
        if (number >= min && number <= max){
            isInRange = true;
        }

        if (!isInRange) {
            for (int num : exception) {
                if (number == num) {
                    isInRange = true;
                    break;
                }
            }
        }

        return isInRange;
    }

    public static void cls() throws IOException, InterruptedException {
            Runtime.getRuntime().exec("cmd /c cls");
    }

}
