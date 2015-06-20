package com.makketmail.fruitbasket;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FruitBasketTest
{
    private static final String TEST_CSV_FILE_PATH = "src/test/resources/basket.csv";

    public FruitBasketTest()
    {
    }

    @Test
    public void testService()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);
    }

    // Total number of fruit: 123
    @Test
    public void testFindAllFruits()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);
        List<Fruit> fruits = fruitBasketService.findAllFruits();

        System.out.println("Total " + fruits.size() + " fruits found");

        for (Fruit fruit : fruits)
        {
            System.out.println(fruit);

        }
    }
    public void testFindAllFruitsByName()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);

        // Get all Apples
        List<Fruit> apples = fruitBasketService.findAllFruitsByName("apple");

        System.out.println("Total Apple " + apples.size());

        for (Fruit apple : apples)
        {
            System.out.println("APPLE -" + apple);
        }
        
        // Get all Oranges
        List<Fruit> oranges = fruitBasketService.findAllFruitsByName("orange");

        System.out.println("Total Orange " + oranges.size());

        for (Fruit orange : oranges)
        {
            System.out.println("ORANGE -" + orange);
        }
    
        
    }
    public void testcountAllFruitsByName()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);
        int countApple = fruitBasketService.countAllFruitsByName( "apple" );
        System.out.println("Total Apple " + countApple);
    
    }

    // Total number of fruit: 123
    @Test
    public void testName()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);
        // int total = fruitBasketService.countAllFruits();
        // System.out.println("Total number of fruit: " + total);
    }

    // Types of fruit: 7
    // The number of each type of fruit in descending order
    // The characteristics (size, color, shape, etc.) of each fruit by type
    // Have any fruit been in the basket for over 3 days
}
