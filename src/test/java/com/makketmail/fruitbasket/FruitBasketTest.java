package com.makketmail.fruitbasket;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FruitBasketTest
{
    @Test
    public void testSummary()
    {
        // This will read -Dcsv=basket.csv"
        String csvFilePath = System.getProperty( "csv" );

        // By Default, this is 3. This can be overridden as system property
        // -DinStoreDays=4

        int inStoreDays = 3;

        try
        {
            inStoreDays = Integer.parseInt( System.getProperty( "inStoreDays", "3" ) );
        }
        catch ( Exception e )
        {
            System.err.println( "Error pasrsing inStoreDays. [" + System.getProperty( "inStoreDays" )
                    + "] is not a valid number" );
        }

        if ( ( null == csvFilePath ) || ( csvFilePath.isEmpty() ) )
        {
            Assert.fail( "Please provide a valid fruit basket CSV file. E.g. mvn clean compile test -DFruitBasketTest -Dcsv=basket.csv" );
        }

        FruitBasketService fruitBasketService = new CsvFruitBasketService( csvFilePath );
        Assert.assertNotNull( fruitBasketService );

        FruitBasketApplication fruitBasketApplication = new FruitBasketApplication();
        fruitBasketApplication.printSummary( fruitBasketService, inStoreDays );
    }
}
