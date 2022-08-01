import java.util.Scanner;

public class Product {
    private String[] products;
    private int[] prices;

    public Product(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
    }

    public void productBasket() {
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

            if (productNum < 0 || productNum > products.length - 1) {
                System.out.println("Проверьте введенные числа: номер товара");
                continue;
            }

            //ниже добавлена возможность вводить отрицательное или нулевое значение товара:
            //если мы введем ноль, то корзина обнулится
            //если мы введем слишком большое отрицательное значение, то корзина тоже обнулится
            // к примеру, в корзине было 2, добавили -7, потом добавили +5
            // тогда итоговый результа будет +5, так как при добалении -7 корзина стала равна нулю

            if(productCount == 0) { // если кол-во товара ввели ноль:
                purchase[productNum] = productCount; //то в массиве кол-во товара становится ноль
            } else if (productCount < 0) { //если кол-во товара ввели со знаком минус:
                purchase[productNum] = Math.max(purchase[productNum] + productCount, 0); // складываем текущее плюсовое кол-во с введенным отрицательным, результат записываем в массив. Если результат отрицательный - меняем на ноль
            } else { //если кол-во товара ввели положительное
                purchase[productNum] += productCount; //прибавляем его к текущему
            }

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
