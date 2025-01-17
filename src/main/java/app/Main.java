package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Список продуктів
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("Smartphone", "Electronics", 800.0)
        );

        // 1. Групування продуктів за категоріями
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        // Виведення групування
        System.out.println("Групування продуктів за категоріями:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        // 2. Обчислення середньої ціни продуктів у кожній категорії
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        // Виведення середніх цін
        System.out.println("\nСередня ціна продуктів у кожній категорії:");
        averagePriceByCategory.forEach((category, avgPrice) -> {
            System.out.println(category + ": " + avgPrice);
        });

        // 3. Знаходження категорії з найвищою середньою ціною
        String categoryWithHighestAveragePrice = averagePriceByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No categories");

        // Виведення результату
        System.out.println("\nКатегорія з найвищою середньою ціною: " + categoryWithHighestAveragePrice);
    }
}
