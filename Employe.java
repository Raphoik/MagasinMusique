
public class Employe extends Personne
{
    private static String idEmploye;



    @Override
    public void run() {
        boolean deconnect = false;
        while (!deconnect) {
            Menus.mainMenu_client();
            int choice = Utilities.intInput("\nChoix : ");
            if (Utilities.isInRange(choice, 1, 2) && choice != 90) {

            } else {
                logOff();
            }
        }
    }
}
