import java.util.*;

public class Main {
    public static void main(String[] args) {

        String[] products = {"Молоко", "Хлеб", "Яблоки", "Сыр"};
        int[] prices = {100, 75, 110, 800};

        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " "
                    + prices[i] + " руб/шт");

        }
        Scanner scanner = new Scanner(System.in);
        int[] purchase = new int[products.length];


        while (true) {
            System.out.println("Введите номер товара и количество или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int productNum;
            int productCount;

            if (parts.length != 2) {
                System.out.println("Некорректный ввод данных. Вводите два числа через пробел");
                continue;
            }
            try {
                productNum = Integer.parseInt(parts[0]) - 1;
                productCount = Integer.parseInt(parts[1]);

            } catch (NumberFormatException e) {
                System.out.println("Следует вводить только числа: сначала - номер товара, затем - пробел и количество");
                continue;
            }

            if (productNum < 0 || productNum > products.length - 1 || productCount <= 0) {
                System.out.println("Проверьте введенные числа: номер товара и количество");
                continue;
            }
            purchase[productNum] += productCount;
        }
        System.out.println();
        System.out.println("Ваша корзина:");
        int totalSum = 0;
        for (int i = 0; i < purchase.length; i++) {
            if (purchase[i] > 0) {
                System.out.println(
                        products[i] + " " +
                        purchase[i] + " шт " +
                        prices[i] + " руб/шт " +
                        purchase[i] * prices[i] + " руб в сумме");
                totalSum += (purchase[i] * prices[i]);
            }
        }
        System.out.println("Итого " + totalSum + " руб");
    }
}