package com.makketmail.fruitbasket;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.makketmail.fruitbasket.exception.FruitBasketException;

public class CsvFruitBasketService implements FruitBasketService
{
    // Fruit attributes
    private static final String   FRUIT_NAME             = "fruit";
    private static final String   FRUIT_DAYS             = "days";
    private static final String   FRUIT_CHARACTERISTIC_1 = "characteristic1";
    private static final String   FRUIT_CHARACTERISTIC_2 = "characteristic2";

    // CSV file header
    private static final String[] FILE_HEADER_MAPPING    =
                                                             {
            FRUIT_NAME,
            FRUIT_DAYS,
            FRUIT_CHARACTERISTIC_1,
            FRUIT_CHARACTERISTIC_2                          };

    private String                csvFilePath            = null;

    public CsvFruitBasketService( String csvFilePath )
    {
        if ( ( null == csvFilePath ) || ( csvFilePath.isEmpty() ) )
        {
            throw new IllegalArgumentException( "A provide valid fruit basket CSV file." );
        }

        this.csvFilePath = csvFilePath;
    }

    public void addfruit() throws FruitBasketException
    {
        throw new UnsupportedOperationException( "Not impemented yet" );
    }

    public List< Fruit > findAllFriuts()
    {
        List< Fruit > availableFruits = readCSV();
        return availableFruits;
    }

    public List< Fruit > findAllFriutsByName( String name )
    {
        List< Fruit > availableFruits = readCSV();
        List< Fruit > requestedFruits = new ArrayList< Fruit >();

        for ( Fruit fruit : availableFruits )
        {
            if ( fruit.getName().equalsIgnoreCase( name ) )
            {
                requestedFruits.add( fruit );
            }
        }

        return requestedFruits;
    }

    public Set< String > findAllFriutsTypes()
    {
        List< Fruit > availableFruits = readCSV();

        Set< String > uniqueFruitNamesSet = new HashSet< String >();

        for ( Fruit fruit : availableFruits )
        {
            uniqueFruitNamesSet.add( fruit.getName() );

        }

        return uniqueFruitNamesSet;
    }

    public List< Fruit > findAllFriutsByEexpiresInDays( String name, Integer expiresIn )
    {
        List< Fruit > availableFruits = readCSV();
        List< Fruit > requestedFruits = new ArrayList< Fruit >();
        for ( Fruit fruit : availableFruits )
        {
            if ( fruit.getName().equalsIgnoreCase( name ) && fruit.getDays() <= expiresIn )
            {
                requestedFruits.add( fruit );
            }
        }

        return requestedFruits;
    }

    public Integer countAllFriutsByName( String name )
    {
        return findAllFriutsByName( name ).size();
    }

    public Integer countAllFriuts()
    {
        return findAllFriuts().size();
    }

    public Integer countAllFriutsTypes()
    {
        return findAllFriutsTypes().size();
    }

    public Integer countAllFriutsByEexpiresInDays( String name, Integer expiresIn )
    {
        return findAllFriutsByEexpiresInDays( name, expiresIn ).size();
    }

    private List< Fruit > readCSV()
    {
        // Create a new list of fruits to be filled by CSV file data
        List< Fruit > fruits = new ArrayList< Fruit >();

        FileReader fileReader = null;

        CSVParser csvFileParser = null;

        // Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader( FILE_HEADER_MAPPING );

        try
        {
            // initialize FileReader object
            fileReader = new FileReader( csvFilePath );

            // initialize CSVParser object
            csvFileParser = new CSVParser( fileReader, csvFileFormat );

            // Get a list of CSV file records
            List< CSVRecord > csvRecords = csvFileParser.getRecords();

            // Read the CSV file records starting from the second record to skip
            // the header
            for ( int i = 1; i < csvRecords.size(); i++ )
            {
                CSVRecord record = csvRecords.get( i );
                // Create a new fruit object and fill his data
                Fruit fruit = new Fruit( record.get( FRUIT_NAME ), Integer.parseInt( record.get( FRUIT_DAYS ) ),
                        record.get( FRUIT_CHARACTERISTIC_1 ), record.get( FRUIT_CHARACTERISTIC_2 ) );
                fruits.add( fruit );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileReader.close();
                csvFileParser.close();
            }
            catch ( IOException e )
            {
                System.out.println( "Error while closing fileReader/csvFileParser !!!" );
            }
        }

        return fruits;

    }

}