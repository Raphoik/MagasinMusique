
/**
 * Created by Gabriel on 2016-11-11.
 */
public class Menus {
    public static void WelcomeScreen(){
        System.out.println("1 - Se connecter en tant que client");
        System.out.println("2 - Se connecter en tant qu'employé");
        System.out.println("3 - Se connecter en tant que gérant");
        System.out.println("4 - Se connecter en tant qu'administrateur systeme");

        System.out.println("\n90 - QUITTER");
    }

    public static String[] LOGIN_MENU(){

        String username = Utilities.input("Utilisateur : ");
        String password = Utilities.hiddenInput("Mot de passe : ");

        return new String[] {username, password};
    }

    public static void mainMenu_client() {
        System.out.println("Vous etes connecté en tant que client. Que voulez-vous faire?");

        System.out.println("\n1 - Rechercher un item");
        System.out.println("2 - Consulter mon panier d'achats");

        System.out.println("\n90 - Se déconnecter");

    }
}

