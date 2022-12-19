package ru.croc.task17.src;

import Item.Item;
import Order.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class TableJDBC {
    private final String connectionUrl;
    private final String user;
    private final String password;
    private final Connection connection;
    private final Statement statement;

    public TableJDBC(String connectionUrl,
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

    public void createTables() {
        String createOrders = "create table if not exists orders " +
                "(numberOrder int primary key auto_increment, " +
                "name varchar(40) NOT NULL, " +
                "articleNumber varchar(40) NOT NULL)";
        try {
            statement.execute(createOrders);
            statement.execute("create table if not exists item(nameGoods varchar, articleNumber varchar primary key, " +
                    "price int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(String tableName, String first, String second, String third) throws SQLException {
        statement.execute("INSERT INTO " + tableName + " VALUES" +
                "(" + first + ", " + second + ", " + third + ")");
    }

    public void fillTable(String pathFile) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(pathFile));
        String line = reader.readLine();
        Set<Order> orderList = new HashSet<Order>();
        Set<Item> itemList = new HashSet<Item>();
        String name, articleNumber, nameGoods, price, numberOrder;
        try {
            statement.execute("delete from orders");
            statement.execute("delete from item");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            String[] fields = line.split(",");
            numberOrder = fields[0];
            name = fields[1];
            articleNumber = fields[2];
            nameGoods = fields[3];
            price = fields[4];
            Boolean is_exists = false;
            for (var item : itemList) {
                if (item.getArticle().equals(articleNumber)) {
                    is_exists = true;
                    break;
                }
            }
            if (!is_exists) {
                itemList.add(new Item(articleNumber, name, Integer.parseInt(price.replace(" ", ""))));
            }
            is_exists = false;
            for (var order : orderList) {
                if (order.getNumberOrder().equals(numberOrder)) {
                    order.addArticle(articleNumber);
                    is_exists = true;
                    break;
                }
            }
            if (!is_exists) {
                orderList.add(new Order(numberOrder, name, articleNumber));
            }
            line = reader.readLine();
        }
        for (var item : itemList) {
            update("ITEM", "'" + item.getName() + "'", "'" + item.getArticle() + "'", Integer.toString(item.getPrice()));
        }
        for (var order : orderList) {
            statement.execute("INSERT INTO " + "ORDERS(name,articleNumber)" + " VALUES" +
                    "(" + "'" + order.getName() + "'" + ", " + "'" + order.getArticle() + "'"+ ")");
        }
    }

}
