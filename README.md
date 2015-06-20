# Fruit Basket Application
Fruit Basket Application Based on CSV file

I) Build (Required Java 1.6 or later and Maven 3 or later)
--------------------------------------------
mvn clean package -Dmaven.test.skip

This command will generate fruit-basket.jar in target folder

II) Run Application from command line (Required Java 1.6 or later)
--------------------------------------------
java -jar fruit-basket.jar basket.csv

III) Run Application with TestNG (Required Java 1.6 or later and Maven 3 or later)
--------------------------------------------
mvn clean compile test -DFruitBasketTest -Dcsv=C:/test/basket.csv
