package business;

import java.util.ArrayList;
import pandesal.Pandesal;
import pandesal.RegularPandesal;
import pandesal.SpecialPandesal;

public class Products {

    public static ArrayList<Pandesal> productList = new ArrayList<>();

    public static void produce() {
        // BASE PANDESAL PRICING
        BasePricing basePricing = new BasePricing(2.0);

        // PRODUCE PRODUCTS
        RegularPandesal regularPandesal = new RegularPandesal(basePricing.getBasePrice());
        SpecialPandesal malunggayPandesal = new SpecialPandesal(
                "Malunggay", basePricing.getBasePrice(), 1.0);
        SpecialPandesal cheesePandesal = new SpecialPandesal(
                "Cheese", basePricing.getBasePrice(), 3.0);
        SpecialPandesal binangkalPandesal = new SpecialPandesal(
                "Binangkal", basePricing.getBasePrice(), 2.0);

        // STORE PRODUCTS IN A COLLECTION
        productList.add(regularPandesal);
        productList.add(malunggayPandesal);
        productList.add(cheesePandesal);
        productList.add(binangkalPandesal);
    }

}
