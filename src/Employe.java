import java.util.ArrayList;

public class Employe extends Personne
{
    private String totalAchat;
    private String totalVente;

    public Employe(ArrayList<String> employeInfo) {
        super(employeInfo);
        this.totalAchat = employeInfo.get(8);
        this.totalVente = employeInfo.get(9);
    }


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


    public ArrayList<String> export_person_info(){
        ArrayList<String> person_info = new ArrayList<String>();
        person_info.add(id);
        person_info.add(prenom);
        person_info.add(nom);
        person_info.add(eMail);
        person_info.add(telephone);
        person_info.add(address);
        person_info.add(type);
        person_info.add(totalAchat);
        person_info.add(totalVente);
        return person_info;
    }

}
