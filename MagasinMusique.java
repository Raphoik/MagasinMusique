

public class MagasinMusique {
    public static void main(String[] args) {

        boolean exit = false;
        while (!exit) {

            Menus.WelcomeScreen();
            int choice = Utilities.intInput("\nChoix : ")
            if (Utilities.isInRange(choice, 1, 4) && choice != 90) {
                String[] credentials = Menus.LOGIN_MENU();
                switch (choice) {
                    case 1:
                        Client client = new Client(credentials[0]);
                        if (client.authenticate(credentials)) {
                            client.run();
                        }
                        break;
                    case 2 :
                        Employe sessionEmploye = new Employe(credentials);
                        break;
                    /*
                    case 3 :
                        SessionGerant sessionGerant = new SessionGerant();
                        break;
                    case 4 :
                        SessionAdmin sessionAdmin = new SessionAdmin();
                        break;
                    */
                }
            } else {
                System.exit(0);
            }
        }
    }
}