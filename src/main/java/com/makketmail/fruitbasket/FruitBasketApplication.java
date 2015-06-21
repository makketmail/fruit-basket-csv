package com.makketmail.fruitbasket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.makketmail.fruitbasket.util.ValueComparator;

public class FruitBasketApplication
{
    public static void main( String[] args )
    {
        // By Default, this is 3. This can be overridden as program argument
        int inStoreDays = 3;

        if ( ( args.length <= 0 ) || ( null == args[ 0 ] ) || ( args[ 0 ].isEmpty() ) )
        {
            System.out.println( "Please provide a valid CSV file.\n" );
            System.out.println( "E.g." );
            System.out.println( "java FruitBasketApplication basket.csv" );
            System.out
                    .println( "\nOptionally you can provide inStore Days too (default is 3). Which will indicate how many days old the fruits are. For example to find fruit which are for over 4 days old\n" );
            System.out.println( "E.g." );
            System.out.println( "java FruitBasketApplication basket.csv 4" );
        }
        else
        {
            String csvPath = args[ 0 ];

            if ( args.length > 1 )
            {
                try
                {
                    inStoreDays = Integer.parseInt( args[ 1 ] );
                }
                catch ( Exception e )
                {
                    System.err.println( "Invalid inStore days " + e.getMessage() );
                }
            }

            FruitBasketService fruitBasketService = new CsvFruitBasketService( csvPath );

            FruitBasketApplication fruitBasketApplication = new FruitBasketApplication();
            fruitBasketApplication.printSummary( fruitBasketService, inStoreDays );
        }
    }

    public void printSummary( FruitBasketService fruitBasketService, int inStoreDays )
    {
        System.out.println( "\nFRUIT BASKET SUMMARY" );
        System.out.println( "==============================================" );
        System.out.println( "Total number of fruit" );
        System.out.println( "----------------------------------------------" );
        printTotal( fruitBasketService );
        System.out.println( "\nTypes of fruit" );
        System.out.println( "----------------------------------------------" );
        printTypeOfFruits( fruitBasketService );
        System.out.println( "\nThe number of each type of fruit in descending order" );
        System.out.println( "----------------------------------------------" );
        printTypeAndSortedCount( fruitBasketService );
        System.out.println( "----------------------------------------------" );
        System.out.println( "\nThe characteristics (size, color, shape, etc.) of each fruit by type" );
        System.out.println( "----------------------------------------------" );
        printCharacteristicsSummary( fruitBasketService );
        System.out.println( "\nHave any fruit been in the basket for over " + inStoreDays + " days" );
        System.out.println( "----------------------------------------------" );
        printFruitsByinStoreDays( fruitBasketService, inStoreDays );
        System.out.println( "==============================================" );
    }

    // Total number of fruit: 123
    private void printTotal( FruitBasketService fruitBasketService )
    {
        int total = fruitBasketService.countAllFruits();
        System.out.println( total );
    }

    // Types of fruit: 7
    private void printTypeOfFruits( FruitBasketService fruitBasketService )
    {
        int fruitCountbyType = fruitBasketService.countAllFruitsTypes();
        System.out.println( fruitCountbyType );
    }

    // The number of each type of fruit in descending order
    private void printTypeAndSortedCount( FruitBasketService fruitBasketService )
    {
        // Key=type/name, Value=count
        Map< String, Integer > typeCountMap = new HashMap< String, Integer >();

        // Get names
        Set< String > uniqueFruitNamesSet = fruitBasketService.findAllFruitsTypes();

        for ( String fruitType : uniqueFruitNamesSet )
        {
            typeCountMap.put( fruitType, fruitBasketService.countAllFruitsByName( fruitType ) );
        }

        Map sortedMap = sortByValue( typeCountMap );

        for ( Object key : sortedMap.keySet() )
        {
            System.out.println( key + " : " + sortedMap.get( key ) );
        }

    }

    private Map< String, Integer > sortByValue( Map< String, Integer > typeCountMap )
    {
        Map sortedMap = new TreeMap( new ValueComparator( typeCountMap ) );
        sortedMap.putAll( typeCountMap );
        return sortedMap;
    }

    // The characteristics (size, color, shape, etc.) of each fruit by type
    private void printCharacteristicsSummary( FruitBasketService fruitBasketService )
    {
        // Expected output format
        // 4 apples: sweet, red
        List< Fruit > fruits = fruitBasketService.findAllFruits();

        // Create Map: Key=FruitName, Value=Set of characteristics (Set is used
        // avoid duplicate characteristics)
        Map< String, Set< String > > characteristicsSetMap = new HashMap< String, Set< String > >();

        for ( Fruit fruit : fruits )
        {
            String fruitName = fruit.getName();

            if ( characteristicsSetMap.containsKey( fruitName ) )
            {
                if ( ( null != fruit.getCharacteristic1() ) && ( fruit.getCharacteristic1() != "" ) )
                {
                    String characteristics = fruit.getCharacteristic1().trim();
                    characteristicsSetMap.get( fruitName ).add( characteristics );
                }

                if ( ( null != fruit.getCharacteristic2() ) && ( fruit.getCharacteristic2() != "" ) )
                {
                    String characteristics = fruit.getCharacteristic2().trim();
                    characteristicsSetMap.get( fruitName ).add( characteristics );
                }

            }
            else
            {
                Set< String > characteristicsSet = new HashSet< String >();

                if ( ( null != fruit.getCharacteristic1() ) && ( fruit.getCharacteristic1() != "" ) )
                {
                    String characteristics = fruit.getCharacteristic1().trim();
                    characteristicsSet.add( characteristics );
                }

                if ( ( null != fruit.getCharacteristic2() ) && ( fruit.getCharacteristic2() != "" ) )
                {
                    String characteristics = fruit.getCharacteristic2().trim();
                    characteristicsSet.add( characteristics );
                }

                characteristicsSetMap.put( fruitName, characteristicsSet );
            }
        }

        // Print output
        // Expected output format
        // 4 apples: sweet, red

        // Get names
        Set< String > uniqueFruitNamesSet = fruitBasketService.findAllFruitsTypes();

        for ( String fruitName : uniqueFruitNamesSet )
        {
            int count = fruitBasketService.countAllFruitsByName( fruitName );
            Set< String > characteristicsSet = characteristicsSetMap.get( fruitName );

            System.out.println( count + " " + fruitName + " : " + characteristicsSet );
        }

    }

    // Have any fruit been in the basket for over 3 days
    private void printFruitsByinStoreDays( FruitBasketService fruitBasketService, int inStoreDays )
    {
        // Expected ourput
        // 4 oranges and 5 pineapples are over 3 days old

        // Get names
        Set< String > uniqueFruitNamesSet = fruitBasketService.findAllFruitsTypes();

        for ( String fruitName : uniqueFruitNamesSet )
        {
            int count = fruitBasketService.countAllFruitsByinStoreDays( fruitName, inStoreDays );

            if ( count > 0 )
            {
                System.out.println( count + " " + fruitName + " : " + inStoreDays + " days old" );
            }
        }
    }
}
