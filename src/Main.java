import java.util.*;

public class Main {
    public static void main(String[] args) {

        String[] products = {"Молоко", "Хлеб", "Яблоки", "Сыр"};
        int[] prices = {100, 75, 110, 800};
        Product food = new Product(products, prices);
        food.productBasket();


    }
}