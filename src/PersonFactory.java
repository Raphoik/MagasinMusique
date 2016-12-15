import java.util.ArrayList;

/**
 * <h1>PersonFactory</h1>
 * La classe <b>PersonFactory</b> sert à construire des objets de type 'Personne'
 * de facon dynamique. L'utilisation devrait etre faite de la manière suivante : une requête est fait dans la
 * base de donnée pour obtenir un ArrayList< String >. Ce tableau est ensuite passé à la factory
 * qui créera un objet du type approprié celon ce qui est contenu à l'index 7 du tableau contenant les informations de
 * la personne.
 *
 * @author Gabriel Cardinal
 * @since 20 décembre 2016
 */

public class PersonFactory {

    /**
     * Crée un objet de type Personne de facon dynamique
     * @param personInfo Tableau représentant toutes les informations d'un usager, incluant son <b>type</b>.
     * @return Retourne l'objet créé de facon dynamique.
     */
    public static Personne makePerson(ArrayList<String> personInfo){

        Personne newPerson = null;

        if (personInfo.get(7).equals("Client")) {
            newPerson = new Client(personInfo);
        }
        else if (personInfo.get(7).equals("Employe")) {
            newPerson = new Employe(personInfo);
        }
        else if (personInfo.get(7).equals("Gerant")) {
            newPerson = new Gerant(personInfo);
        }
        else if (personInfo.get(7).equals("Admin")) {
            newPerson = new Admin(personInfo);
        }

        return newPerson;
    }
}