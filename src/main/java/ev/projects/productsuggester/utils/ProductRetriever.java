package ev.projects.productsuggester.utils;

import ev.projects.productsuggester.models.Product;

public class ProductRetriever {

    public static Product getCurrentAccountProduct() {
        return new Product("Current Account");
    }

    public static Product getCurrentAccountPlusProduct() {
        return new Product("Current Account Plus");
    }

    public static Product getJuniorSaverAccountProduct() {
        return new Product("Junior Saver Account");
    }

    public static Product getStudentAccountProduct() {
        return new Product("Student Account");
    }

    public static Product getSeniorAccountProduct() {
        return new Product("Senior Account");
    }

    public static Product getDebitCardProduct() {
        return new Product("Debit Card");
    }

    public static Product getCreditCardProduct() {
        return new Product("Credit Card");
    }

    public static Product getGoldCreditCardProduct() {
        return new Product("Gold Credit Card");
    }

}