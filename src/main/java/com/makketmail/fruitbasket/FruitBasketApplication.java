package com.makketmail.fruitbasket;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Main Entry point through cmd
 * 
 * @author Anupama Bbaurajan
 *
 */
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

    /**
     * Prints the fruit basket summary
     * @param fruitBasketService
     * @param inStoreDays
     */
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

        Map< String, Integer > sortedMap = sortByComparator( typeCountMap );

        for ( Object key : sortedMap.keySet() )
        {
            System.out.println( key + " : " + sortedMap.get( key ) );
        }

    }

    private Map< String, Integer > sortByComparator( Map< String, Integer > unsortMap )
    {
        // Convert Map to List
        List< Map.Entry< String, Integer >> list = new LinkedList< Map.Entry< String, Integer >>( unsortMap.entrySet() );

        // For ascending
        // Collections.sort( list, new Comparator< Map.Entry< String, Integer...

        // For descending
        // Collections.sort( list, Collections.reverseOrder( new Comparator<
        // Map.Entry< String, Integer...

        // Sort list with comparator, to compare the Map values. Descending
        Collections.sort( list, Collections.reverseOrder( new Comparator< Map.Entry< String, Integer >>()
        {
            public int compare( Map.Entry< String, Integer > o1, Map.Entry< String, Integer > o2 )
            {
                return ( o1.getValue() ).compareTo( o2.getValue() );
            }
        } ) );

        // Convert sorted map back to a Map
        Map< String, Integer > sortedMap = new LinkedHashMap< String, Integer >();
        for ( Iterator< Map.Entry< String, Integer >> it = list.iterator(); it.hasNext(); )
        {
            Map.Entry< String, Integer > entry = it.next();
            sortedMap.put( entry.getKey(), entry.getValue() );
        }
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


    private void printFruitsByinStoreDays( FruitBasketService fruitBasketService, int inStoreDays )
    {


        boolean isFruitsFound = false;

        // Get names
        Set< String > uniqueFruitNamesSet = fruitBasketService.findAllFruitsTypes();

        for ( String fruitName : uniqueFruitNamesSet )
        {
            int count = fruitBasketService.countAllFruitsByinStoreDays( fruitName, inStoreDays );

            if ( count > 0 )
            {
                System.out.println( count + " " + fruitName + " : " + inStoreDays + " days old" );
                isFruitsFound = true;
            }
        }

        if ( !isFruitsFound )
        {
            System.out.println( "No fruits found which is " + inStoreDays + " days old" );
        }
    }
}
