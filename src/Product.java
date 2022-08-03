import java.util.Scanner;

public class Product {
    private String[] products;
    private int[] prices;
    private String[] sale;

    public Product(String[] products, int[] prices, String[] sale) {
        this.products = products;
        this.prices = prices;
        this.sale = sale;
    }

    public void setSale(String[] sale) {
        this.sale = sale;
    }

    public void productBasket() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Список возможных товаров для покупки: \n");
        for (int i = 0; i < products.length; i++) {
            sb1.append(i + 1);
            sb1.append(". ");
            sb1.append(products[i]);
            sb1.append(" ");
            sb1.append(prices[i]);
            sb1.append(" руб/шт\n");
        }
        sb1.append("\nАкция 3 по цене 2 на товары: \n");
        for (int i = 0; i < sale.length; i++) {
            sb1.append(sale[i]);
            if (i != sale.length - 1) {
                sb1.append(", ");
            } else {
                sb1.append(". \n");
            }
        }

        System.out.println(sb1.toString());

        Scanner scanner = new Scanner(System.in);
        int[] purchase = new int[products.length];


        while (true) {
            System.out.println("Введите через пробел номер товара и количество товара или введите 'end' для завершения покупки:");
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
            // тогда итоговый результат будет +5, так как при добалении -7 корзина стала равна нулю

            if (productCount == 0) { // если кол-во товара ввели ноль:
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
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < purchase.length; i++) {
            if (purchase[i] > 0) {
                int amount;
                if (purchase[i] > 2 && isSale(products[i])) {
                    amount = ((purchase[i] - purchase[i] / 3) * prices[i]);
                    prices[i] = amount / purchase[i];
                } else {
                    amount = purchase[i] * prices[i];
                }
                sb2.append(products[i]);
                sb2.append(" ");
                sb2.append(purchase[i]);
                sb2.append(" шт ");
                sb2.append(prices[i]);
                sb2.append(" руб/шт ");
                sb2.append(amount);
                sb2.append(" руб в сумме\n");

                totalSum += amount;
            }
        }
        sb2.append("Итого: ");
        sb2.append(totalSum);
        sb2.append(" руб");
        System.out.println(sb2.toString());
    }

    public boolean isSale(String product) {
        for (String elem : sale) {
            if (elem != null && elem.equals(product)) {
                return true;
            }
        }
        return false;
    }

}
