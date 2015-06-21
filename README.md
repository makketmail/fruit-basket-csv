# Fruit Basket Application
Fruit Basket Application Based on CSV file


## Environment

Purpose  | Tool
------------- | -------------
Language |Java 1.6
Build Tool |Maven 3
IDE |Eclipse
Testing Framework |TestNG
Source Control |GIT


I) Build (Requires Java 1.6 or later and Maven 3 or later)
--------------------------------------------
```
mvn clean package -Dmaven.test.skip
```

This command will generate fruit-basket.jar in target folder

(I have already created this and copied to the build folder)

II) Run Application from command line (Requires Java 1.6 or later)
--------------------------------------------
```
java -jar fruit-basket.jar basket.csv
```

Optionally you can provide inStore Days too (default is 3) as an argument. Which will indicate how many days old the fruits are. For example to find fruit which are for over 4 days old

```
java FruitBasketApplication basket.csv 4
```

III) Run Application with TestNG (Requires Java 1.6 or later and Maven 3 or later)
--------------------------------------------
```
mvn clean compile test -DFruitBasketTest -Dcsv=C:/test/basket.csv
```

Optionally you can provide inStore Days too (default is 3) as system property. Which will indicate how many days old the fruits are. For example to find fruit which are for over 4 days old

```
mvn clean compile test -DFruitBasketTest -Dcsv=C:/test/basket.csv -DinStoreDays=4
```
