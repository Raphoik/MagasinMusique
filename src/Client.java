import java.util.ArrayList;

public final class Client extends Personne {

    private String totalAchats;

    public Client(ArrayList<String> clientInfo) {
        super(clientInfo);
        this.totalAchats = clientInfo.get(8);
    }

    public String getTotalAchats() {
        return totalAchats;
    }

    public void setTotalAchats(String totalAchats) {
        this.totalAchats = totalAchats;
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
        person_info.add(totalAchats);
        return person_info;
    }
}