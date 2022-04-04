# Charlene's Coffee Corner

Recently, Charlene decided to open her very own little coffee shop on a busy street corner.
Being the careful entrepreneur, she decided to start off with a limited offering, with the option to expand her choice of products, as  business goes.
Her Offering
- Coffee (small, medium, large) 2.50 CHF, 3.00 CHF, 3.50 CHF
- Bacon Roll 4.50 CHF
- Freshly squeezed orange juice (0.25l) 3.95 CHF

Extras:
- Extra milk 0.30 CHF
- Foamed milk 0.50 CHF
- Special roast coffee 0.90 CHF 

The application is written using JDK17 and maven

The application loads the menu and extras from file, so it can be extended very easily.
The files introduce a many-to-many relation between menu items and extras i.e. the extras are available only for a few selected items. 

Unit test coverage is above 80% for all classes apart from `Main.java`

To run application
```
 mvn clean package && java -jar target/charlenes-coffee-corner-1.0-SNAPSHOT.jar
```

This will prompt you with an interactive menu. 

To exit application type -1. 


