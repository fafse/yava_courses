package ru.croc.task18.src;
import java.io.BufferedReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class OrderDataBaseDao {
    String connectionUrl = "jdbc:h2:/DataBaseFiles/ShopDataBase2";
    static final String user = "sa";
    static final String password = "";
    public static void update(Connection connection, String tableName, String first, String second, String third) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("INSERT INTO " + tableName +  " VALUES" +
                    "(" + first + ", " + second + ", " + third+")");            System.out.println("here");

        }
    }
    public Order createOrder(String userLogin, List<Product> products) {
    /*Создание заказа.
    Для указанного пользователя в базе данных создается новый заказ с заданным списком товаров.*/
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)){
            try (Statement statement = conn.createStatement()) {
                Integer number;
                try (ResultSet result = statement.executeQuery("SELECT max(numberorder) FROM ORDERS")) {
                if(result.next())
                {

                    number = result.getInt("MAX(NUMBERORDER)") + 1;

                }else
                {
                    number = 0;
                }

                    for (var product:
                         products) {

                        update(conn,"orders", "'" + Integer.toString(number)+"'" ,"'" + product.getNameGoods()+"'","'" +product.getArticleNumber()+"'");
                        number++;

                    }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
