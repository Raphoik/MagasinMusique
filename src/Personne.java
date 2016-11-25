public abstract class Personne
{
    protected String prenom;
    protected String nom;
    protected String telephone;
    protected String mobile;
    protected String eMail;


    public Personne(){
        incrementId();
    }


    private static String id = "0";

    public static boolean authenticate(String[] credentials){

        /*
        TODO BD check
         */

        boolean credentials_OK = false;

        if (credentials[0].equals("client") && credentials[1].equals("invite")){
            credentials_OK = true;
        }

        return credentials_OK;
    }

    private void incrementId(){
        int int_id = Integer.parseInt(id);
        int_id++;
        this.id = Integer.toString(int_id);
    }

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

    public void logOff(){
        /*
        TODO actions on DB
        */

        // ****STACK OVERFLOW****
        System.out.println("\n" + this.prenom + " s'est déconnecté\n");
        MagasinMusique.main(new String[] {});

    }
}