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
    private String connectionUrl = "jdbc:h2:/DataBaseFiles/ShopDataBase10";
    private static final String user = "sa";
    private static final String password = "";
    public void createTables()
    {
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)){
            Statement statement = conn.createStatement();
            String createOrders = "create table if not exists orders " +
                    "(numberOrder integer NOT NULL, " +
                    "name varchar(40) NOT NULL, " +
                    "articleNumber varchar(40) NOT NULL)";
            statement.execute(createOrders);
            statement.execute("create table if not exists item(nameGoods varchar, articleNumber varchar primary key, " +
                    "price int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Connection connection, String tableName, String first, String second, String third) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("INSERT INTO " + tableName +  " VALUES" +
                    "(" + first + ", " + second + ", " + third+")");
        }
    }

    public void fillTable(String pathFile) throws IOException {
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)){
            BufferedReader reader = new BufferedReader(new FileReader(pathFile));
            String line = reader.readLine();
            Set<Order> orderList= new HashSet<Order>();
            Set<Item> itemList= new HashSet<Item>();
            String name,articleNumber,nameGoods,price,numberOrder;
            try (Statement statement = conn.createStatement()){
                statement.execute("delete from orders");
                statement.execute("delete from item");
            }
            while (line != null) {
                String[] fields = line.split(",");
                numberOrder=fields[0];
                name = fields[1];
                articleNumber=fields[2];
                nameGoods=fields[3];
                price=fields[4];
                Boolean is_exists=false;
                for (var item:itemList)
                {
                    if(item.getArticle().equals(articleNumber))
                    {
                        is_exists=true;
                        break;
                    }
                }
                if(!is_exists)
                {
                    itemList.add(new Item(articleNumber,name,Integer.parseInt(price.replace(" ",""))));
                }
                is_exists=false;
                for (var order:orderList)
                {
                    if(order.getNumberOrder().equals(numberOrder))
                    {
                        order.addArticle(articleNumber);
                        is_exists=true;
                        break;
                    }
                }
                if(!is_exists){
                    orderList.add(new Order(numberOrder,name,articleNumber));
                }
                line = reader.readLine();
            }
            for(var item:itemList)
            {
                update(conn, "ITEM",  "'" + item.getName() + "'","'" +  item.getArticle() + "'", Integer.toString(item.getPrice()));
            }
            for(var order:orderList)
            {
                update(conn, "ORDERS",  "'" + order.getNumberOrder() + "'","'" +  order.getName() + "'", "'"+order.getArticle()+"'");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
