package com.makketmail.fruitbasket;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FruitBasketTest
{
    @Test
    public void testSummary()
    {
        // This will read -Dcsv=basket.csv"
        String csvFilePath = System.getProperty("csv");

        if ((null == csvFilePath) || (csvFilePath.isEmpty()))
        {
            Assert.fail("Please provide a valid fruit basket CSV file. E.g. mvn clean compile test -DFruitBasketTest -Dcsv=basket.csv");
        }

        FruitBasketService fruitBasketService = new CsvFruitBasketService(csvFilePath);
        Assert.assertNotNull(fruitBasketService);

        FruitBasketApplication fruitBasketApplication = new FruitBasketApplication();
        fruitBasketApplication.printSummary(fruitBasketService);
    }
}
