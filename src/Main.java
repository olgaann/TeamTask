public class Main {
    public static void main(String[] args) {

        String[] products = {"Молоко", "Хлеб", "Яблоки", "Сыр"};
        double[] prices = {100, 75, 110, 800};
        String[] foodSale = {"Яблоки", "Сыр"};
        Product food = new Product(products, prices, foodSale);
        food.productBasket();


    }
}