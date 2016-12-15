import java.util.ArrayList;

public abstract class Produit
{
    protected String id;
    protected String type;
    protected String marque;
    protected String desc;
    protected String prix;
    protected String prix_cos;
    protected String qte;

    public Produit(ArrayList<String> personInfo) {
        this.id = personInfo.get(0);
        this.type = personInfo.get(1);
        this.marque = personInfo.get(2);
        this.desc = personInfo.get(3);
        this.prix = personInfo.get(4);
        this.prix_cos = personInfo.get(5);
        this.qte = personInfo.get(6);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPrix_cos() {
        return prix_cos;
    }

    public void setPrix_cos(String prix_cos) {
        this.prix_cos = prix_cos;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }





    public ArrayList<String> export_product_info(){
        ArrayList<String> product_info = new ArrayList<String>();
        product_info.add(id);
        product_info.add(type);
        product_info.add(marque);
        product_info.add(desc);
        product_info.add(prix);
        product_info.add(prix_cos);
        product_info.add(qte);
        return product_info;
    }
}
