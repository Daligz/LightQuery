# LightQuery
Light query is a simple SQL query builder.


## Features
* Create tables with simple query syntax.
* Select, insert, update and delete values with default java data types.
* Use a Hikari pool class already defined.

## How to use LightQuery
**Values used in the examples**\
**LOGGER** is a instance of Logger from log4j\
**TBL_NAME** is the table name "tbl_test"

### Using Hikari Pool
You just need to create an instance of the HikariPool class and use the HikariConfig class to define the configuration.
```java
private static final HikariPool HIKARI_POOL = new HikariPool(new HikariConfig("src/main/resources/database.properties"));
```


### Create table with LightQuery
To create a table use light query syntax, in the example create a table with id, player name and first join.

SQL data types are simplified to three characters:
* VARCHAR = STR
* INT = INT
* BIT = BIT
* BOOLEAN = BOO
* CHAR = CHA
* DATE = DAT
* DECIMAL = DEC
* DOUBLE = DOU
* FLOAT = FLO
* NULL = NULL

The statement "TABLE =>" indicates the creation of a table.\
To place the length of a data type ":" is used, for some values it is not necessary to specify it such as DAT and BOO.\

The sentence "->" indicates the use of properties, these were simplified.
* NOT NULL = NN
* AUTO_INCREMENT = AI
* PRIMARY KEY = PK

To indicate the column in the primary key you must use ":" and place the name\
*Use "," to separate the instructions*


**The table is only created if it does not exist.**
```java
public void createTable() {
    final String lQueryFormat = new LCreateTable(
            "TABLE => id INT:4 -> NN AI, playerName STR:25 -> NN, firstJoin DAT -> NN, PK:id"
    ).getQuery();
    final String lQuery = String.format(lQueryFormat, TBL_NAME);
    HIKARI_POOL.execute(connection -> {
        final boolean response = connection.prepareStatement(lQuery).execute();
        LOGGER.info(String.format("(Create) lQuery executed with response %b \n lQuery: %s", response, lQuery));
        return null;
    });
}
```

### Insert with LightQuery
Light query detects the java data types, to insert data it can be used in a very simple way.
```java
public void insert() {
    final String lQuery = new LInsert()
            .table(TBL_NAME)
            .values(
                    null, "ImSrPanda", new Date(System.currentTimeMillis())
            )
            .getQuery();
    HIKARI_POOL.execute(connection -> {
        final boolean response = connection.prepareStatement(lQuery).execute();
        LOGGER.info(String.format("(Insert) lQuery executed with response %b \n lQuery: %s", response, lQuery));
        return null;
    });
}
```

### Select with LightQuery
LSelect include a where sentence to get specific data.\
*In this example all the data is obtained from the table.*
```java
public void select() {
    final String lQuery = new LSelect().from(TBL_NAME).value("*").getQuery();
    HIKARI_POOL.execute(connection -> {
        final ResultSet resultSet = connection.prepareStatement(lQuery).executeQuery();
        while (resultSet.next()) {
            final int id = resultSet.getInt("id");
            final String playerName = resultSet.getString("playerName");
            final Date firstJoin = resultSet.getDate("firstJoin");
            LOGGER.info(String.format("  + (Select) id: %s, player: %s, firstJoin: %s", id, playerName, firstJoin.toString()));
        }
        LOGGER.info(String.format("(Select) lQuery executed! \n lQuery: %s", lQuery));
        return null;
    });
}
```

### Update with LightQuery
You can use the update statement as many times as you need.
```java
    public void update() {
        final String lQuery = new LUpdate()
                .table(TBL_NAME)
                .update("playerName", "Panda")
                .where("playerName", "=", "ImSrPanda")
                .getQuery();
        HIKARI_POOL.execute(connection -> {
            final boolean response = connection.prepareStatement(lQuery).execute();
            LOGGER.info(String.format("(Update) lQuery executed with response %b \n lQuery: %s", response, lQuery));
            return null;
        });
    }
```

### Delete with LightQuery
```java
    public void delete() {
        final String lQuery = new LDelete()
                .from(TBL_NAME)
                .where("playerName", "=", "Panda")
                .getQuery();
        HIKARI_POOL.execute(connection -> {
            final boolean response = connection.prepareStatement(lQuery).execute();
            LOGGER.info(String.format("(Delete) lQuery executed with response %b \n lQuery: %s", response, lQuery));
            return null;
        });
    }
```
