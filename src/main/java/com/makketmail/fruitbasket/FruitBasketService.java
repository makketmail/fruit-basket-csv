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

    public List< Fruit > findAllFruitsByEexpiresInDays( String name, Integer expiresIn );

    public Integer countAllFruits();

    public Integer countAllFruitsByName( String name );

    public Integer countAllFruitsTypes();

    public Integer countAllFruitsByEexpiresInDays( String name, Integer expiresIn );
}
