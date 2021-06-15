# LightQuery
Light query is a simple SQL query builder.


## Features
* Create tables with simple query syntax.
* Select, insert, update and delete values with default java data types.
* Use a Hikari pool class already defined.

## Using Hikari Pool
You just need to create an instance of the HikariPool class and use the HikariConfig class to define the configuration.
```java
private static final HikariPool HIKARI_POOL = new HikariPool(new HikariConfig("src/main/resources/database.properties"));
```


## Create table with LightQuery
```java

```
