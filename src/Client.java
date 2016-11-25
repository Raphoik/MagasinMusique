public final class Client extends Personne
{

    private int totalAchats;

    public Client(String username) {
        String [] clientInfo = clientInfo();

        this.prenom = clientInfo[0];
        this.nom = clientInfo[1];
        this.telephone = clientInfo[2];
        this.mobile = clientInfo[3];
        this.eMail = clientInfo[4];
    }

    public String getPrenom(){
        return this.prenom;
    }
    public String getNom(){
        return this.nom;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public String getmMbile(){
        return this.mobile;
    }
    public String geteMail(){
        return this.eMail;
    }

    public void setPrenom(String n_prenom){
        this.prenom = n_prenom;
    }
    public void setNom(String n_Nom){
        this.nom = n_Nom;
    }
    public void setTelephone(String n_Telephone){
        this.telephone = n_Telephone;
    }
    public void setMobile(String n_mobile){
        this.mobile = n_mobile;
    }
    public void seteMail(String n_eMail){
        this.eMail = n_eMail;
    }


    private String[] clientInfo(){

        /*
        TODO retreive data from DB. Hardcoded for now.
         */

        String prenom = "Lionel";
        String nom = "Morreau";
        String telephone = "819477-0543";
        String mobile = "8198171234";
        String email = "lionel@bonjour.com";

        return new String[] {prenom, nom, telephone, mobile, email};
    }
}