package ru.croc.task17.src;


import java.io.IOException;
import java.sql.SQLException;

public class Task17 {

    public static void main(String[] args) throws IOException {

        ru.croc.task17.src.TableJDBC tableJDBC = new ru.croc.task17.src.TableJDBC();
        tableJDBC.createTables();
            tableJDBC.filltable("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task17\\resources\\orders.csv");
    }
}
/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Task17 {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\k5469\\Downloads\\Task17\\Task17\\DataBaseFiles\\BuyHistory.csv"));
        List<String> databaseData = r.lines().toList();
        src.DataBaseWriter wr = new src.DataBaseWriter();
        wr.fillDataBase("jdbc:h2:/DatabaseFiles/shopDataBase", "sa", "");
    }
}*/