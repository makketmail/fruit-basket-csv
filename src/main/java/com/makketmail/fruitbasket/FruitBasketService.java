package com.makketmail.fruitbasket;

import java.util.List;
import java.util.Set;

import com.makketmail.fruitbasket.exception.FruitBasketException;

public interface FruitBasketService
{
    public void addfruit() throws FruitBasketException;

    public List< Fruit > findAllFruits();

    public List< Fruit > findAllFruitsByName( String name );

    public Set< String > findAllFruitsTypes();

    public List< Fruit > findAllFruitsByinStoreDays( String name, Integer istoreDays );

    public Integer countAllFruits();

    public Integer countAllFruitsByName( String name );

    public Integer countAllFruitsTypes();

    public Integer countAllFruitsByinStoreDays( String name, Integer istoreDays );
}
