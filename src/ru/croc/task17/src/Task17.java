package ru.croc.task17.src;


import java.io.IOException;
import java.sql.SQLException;

public class Task17 {

    public static void main(String[] args) throws IOException {

        ru.croc.task17.src.TableJDBC tableJDBC = new ru.croc.task17.src.TableJDBC("jdbc:h2:/DataBaseFiles/ShopDataBase10", "sa", "");
        tableJDBC.createTables();
        try {
            tableJDBC.fillTable(args[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
