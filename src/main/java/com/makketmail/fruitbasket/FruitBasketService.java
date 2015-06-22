package com.makketmail.fruitbasket;

import java.util.List;
import java.util.Set;

/**
 * FruitBasketService API
 * 
 * @author Anupama Bbaurajan
 *
 */
public interface FruitBasketService
{
    /**
     * Returns all the available fruits
     * 
     * @return - list of fruits
     */
    public List< Fruit > findAllFruits();

    /**
     * Returns all the available fruits based on the i/p param
     * 
     * @param name
     *            - Fruit Name
     * @return - list of fruits
     */
    public List< Fruit > findAllFruitsByName( String name );

    /**
     * Returns all the available fruit types
     * 
     * @return - Set of fruits
     */
    public Set< String > findAllFruitsTypes();

    /**
     * Returns all the fruits those are in store for more than inStore days
     * 
     * @param name
     * @param istoreDays
     * @return - List of fruits
     */
    public List< Fruit > findAllFruitsByinStoreDays( String name, Integer istoreDays );

    /**
     * Reurns the total number of fruits available
     * 
     * @return
     */
    public Integer countAllFruits();

    /**
     * Returns the count of fruits by i/p param
     * 
     * @param name
     * @return
     */
    public Integer countAllFruitsByName( String name );

    /**
     * Returns the total number of fruit types available
     * 
     * @return
     */
    public Integer countAllFruitsTypes();

    /**
     * Returns the total count of fruits those are in store for more than
     * inStoreDays
     * 
     * @param name
     * @param istoreDays
     * @return
     */
    public Integer countAllFruitsByinStoreDays( String name, Integer istoreDays );
}
