package com.makketmail.fruitbasket;

import java.util.List;
import java.util.Set;

import com.makketmail.fruitbasket.exception.FruitBasketException;

public interface FruitBasketService
{
    public void addfruit() throws FruitBasketException;

    public List< Fruit > findAllFriuts();

    public List< Fruit > findAllFriutsByName( String name );

    public Set< String > findAllFriutsTypes();

    public List< Fruit > findAllFriutsByEexpiresInDays( String name, Integer expiresIn );

    public Integer countAllFriuts();

    public Integer countAllFriutsByName( String name );

    public Integer countAllFriutsTypes();

    public Integer countAllFriutsByEexpiresInDays( String name, Integer expiresIn );
}
