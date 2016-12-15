import java.util.ArrayList;

public abstract class Personne
{
    protected String id;
    protected String prenom;
    protected String nom;
    protected String eMail;
    protected String telephone;
    protected String address;
    protected String type;



    public Personne(ArrayList<String> personInfo) {
        this.id = personInfo.get(0);
        this.prenom = personInfo.get(1);
        this.nom = personInfo.get(2);
        this.eMail = personInfo.get(4);
        this.telephone = personInfo.get(5);
        this.address = personInfo.get(6);
        this.type = personInfo.get(7);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ArrayList<String> export_person_info(){
        ArrayList<String> person_info = new ArrayList<String>();
        person_info.add(id);
        person_info.add(prenom);
        person_info.add(nom);
        person_info.add(eMail);
        person_info.add(telephone);
        person_info.add(address);
        person_info.add(type);
        return person_info;
    }
}