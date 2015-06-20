package com.makketmail.fruitbasket;

public class Fruit
{
    // name and type are same
    private String name;
    private int    days;
    private String characteristic1;
    private String characteristic2;

    public Fruit()
    {
        // TODO Auto-generated constructor stub
    }

    public Fruit(String name, int days, String characteristic1, String characteristic2)
    {
        super();
        this.name = name;
        this.days = days;
        this.characteristic1 = characteristic1;
        this.characteristic2 = characteristic2;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getDays()
    {
        return days;
    }

    public void setDays(int days)
    {
        this.days = days;
    }

    public String getCharacteristic1()
    {
        return characteristic1;
    }

    public void setCharacteristic1(String characteristic1)
    {
        this.characteristic1 = characteristic1;
    }

    public String getCharacteristic2()
    {
        return characteristic2;
    }

    public void setCharacteristic2(String characteristic2)
    {
        this.characteristic2 = characteristic2;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "[name=" + getName() + " ,days=" + getDays() + " ,characteristic1="
          + getCharacteristic1() + " ,characteristic2=" + getCharacteristic2() + "]";
    }
}
