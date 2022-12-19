package Order;

import java.io.BufferedReader;

import Product.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class OrderDataBaseDao {
    private String connectionUrl;
    private final String user;
    private final String password;
    private final Connection connection;
    private final Statement statement;

    public OrderDataBaseDao(String connectionUrl,
                            String user,
                            String password) {
        this.connectionUrl = connectionUrl;
        this.user = user;
        this.password = password;
        try {
            connection = DriverManager.getConnection(connectionUrl,
                    user,
                    password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String tableName,
                       String first,
                       String second) throws SQLException {

        statement.execute("INSERT INTO " + tableName + " VALUES" + "(" +
                first + ", " +
                second +")");
    }

    public Order createOrder(String userLogin,
                             List<Product> products) {//change
    /*Создание заказа.
    Для указанного пользователя в базе данных создается новый заказ с заданным списком товаров.*/
        Order order=new Order(userLogin,products);


                update("orders",
                        "'" + product.getNameGoods() + "'",
                        "'" + product.getArticleNumber() + "'");
        return order;
    }
}
