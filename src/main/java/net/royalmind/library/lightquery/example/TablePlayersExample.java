package net.royalmind.library.lightquery.example;

import net.royalmind.library.lightquery.annotations.Table;

@Table(
        name = "TBL_PLAYERS",
        create = true,
        values = "TABLE => id INT:4 -> NN AI, playerName STR:5 -> NN, PK:id"
)
public class TablePlayersExample { }
