package com.makketmail.fruitbasket;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FruitBasketServiceTest
{
    private static final String TEST_CSV_FILE_PATH = "src/test/resources/basket.csv";

    public FruitBasketServiceTest()
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
    public void testFindAllFriuts()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);
        List<Fruit> fruits = fruitBasketService.findAllFriuts();

        System.out.println("Total " + fruits.size() + " fruits found");

        for (Fruit fruit : fruits)
        {
            System.out.println(fruit);

        }
    }

    // Total number of fruit: 123
    @Test
    public void testName()
    {
        FruitBasketService fruitBasketService = new CsvFruitBasketService(TEST_CSV_FILE_PATH);
        Assert.assertNotNull(fruitBasketService);
        // int total = fruitBasketService.countAllFriuts();
        // System.out.println("Total number of fruit: " + total);
    }

    // Types of fruit: 7
    // The number of each type of fruit in descending order
    // The characteristics (size, color, shape, etc.) of each fruit by type
    // Have any fruit been in the basket for over 3 days
}
