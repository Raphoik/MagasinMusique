/**
 * <h1>MagasinMusique</h1>
 * La classe <b>MagasinMusique</b> est la classe principale du programme.
 * Elle est responsable de gérer la connexion des differents usagers (clients, employés, gérants ou admins).
 * Une fois qu'un usager a établi une connexion avec la base de donnée, le flot de controle est passé à
 * l'objet résultant de cette connexion (client, employé, gérant ou admin).
 *
 * @author Gabriel Cardinal
 * @since 20 décembre 2016
 */

public class MagasinMusique {

    /**
     * La methode 'main' gère le flot de control initial du programme. Elle est le <b>seul</b> point de sortie normal
     * du programme.
     *
     * @param args Innutilisé. Le paramètre 'args' de la méthode principale est innutilisé car
     *             puisque le programme n'est pas designé pour être lancé en ligne de commande.
     */
    public static void main(String[] args) {
        SQLiteJDBC maBD = new SQLiteJDBC();
        maBD.get_person("id","2");
    }
}