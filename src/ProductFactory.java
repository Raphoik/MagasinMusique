import java.util.ArrayList;

public class ProductFactory {

    public static Produit makeProduct(ArrayList<String> product_info){

        Produit new_product = null;

        if (product_info.get(1).equals("Corde")) {
            new_product = new InstrumentCorde(product_info);
        }
        else if (product_info.get(1).equals("Percussion")) {
            new_product = new InstrumentPercussion(product_info);
        }
        else if (product_info.get(1).equals("Vent")) {
            new_product = new InstrumentVent(product_info);
        }
        else if (product_info.get(1).equals("CarteSon")) {
            new_product = new CarteSon(product_info);
        }
        else if (product_info.get(1).equals("Console")) {
            new_product = new Console(product_info);
        }

        return new_product;
    }

}
