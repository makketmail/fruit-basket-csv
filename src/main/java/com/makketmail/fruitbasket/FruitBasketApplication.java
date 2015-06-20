package com.makketmail.fruitbasket;

import java.util.List;

public class FruitBasketApplication
{
    
    public static void main(String[] args)
    {
        if (args.length <= 0)
        {
            System.out.println("Please provide a valid CSV file.\n");
            System.out.println("E.g.");
            System.out.println("java FruitBasketApplication basket.csv");
            System.out.println("java FruitBasketApplication C:/test/basket.csv");
        }
        else
        {
            String csvPath = args[0];

            printSummary(csvPath);
        }
    }

    private static void printSummary(String csvPath)
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(csvPath);

        List<Fruit> fruits = fruitBasketService.findAllFriuts();

        System.out.println("Total " + fruits.size() + " fruits found");

        for (Fruit fruit : fruits)
        {
            System.out.println(fruit);

        }

        int countOfFruits=fruitBasketService.countAllFriuts();
        System.out.println("Total number of fruits " + countOfFruits);
    }
}
