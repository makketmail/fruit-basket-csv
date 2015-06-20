package com.makketmail.fruitbasket;

public class FruitBasketApplication
{
    public static void main( String[] args )
    {
        if ( ( args.length <= 0 ) || ( null == args[ 0 ] ) || ( args[ 0 ].isEmpty() ) )
        {
            System.out.println( "Please provide a valid CSV file.\n" );
            System.out.println( "E.g." );
            System.out.println( "java FruitBasketApplication basket.csv" );
            System.out.println( "java FruitBasketApplication C:/test/basket.csv" );
        }
        else
        {
            String csvPath = args[ 0 ];
            FruitBasketService fruitBasketService = new CsvFruitBasketService( csvPath );

            FruitBasketApplication fruitBasketApplication = new FruitBasketApplication();
            fruitBasketApplication.printSummary( fruitBasketService );
        }
    }

    public void printSummary( FruitBasketService fruitBasketService )
    {
        // Total number of fruit: 123
        // Types of fruit: 7
        // The number of each type of fruit in descending order
        // The characteristics (size, color, shape, etc.) of each fruit by type
        // Have any fruit been in the basket for over 3 days
        int total = fruitBasketService.countAllFruits();

        System.out.println( "\nFRUIT BASKET SUMMARY" );
        System.out.println( "==============================================" );
        System.out.println( "Total number of fruit" );
        System.out.println( "----------------------------------------------" );
        System.out.println( total );

        System.out.println( "\nTypes of fruit" );
        System.out.println( "----------------------------------------------" );
        System.out.println( "TODO" );

        System.out.println( "\nThe number of each type of fruit in descending order" );
        System.out.println( "----------------------------------------------" );
        System.out.println( "TODO" );

        System.out.println( "\nThe characteristics (size, color, shape, etc.) of each fruit by type" );
        System.out.println( "----------------------------------------------" );
        System.out.println( "TODO" );

        System.out.println( "\nHave any fruit been in the basket for over 3 days" );
        System.out.println( "----------------------------------------------" );
        System.out.println( "TODO" );
        System.out.println( "==============================================" );
    }
}
