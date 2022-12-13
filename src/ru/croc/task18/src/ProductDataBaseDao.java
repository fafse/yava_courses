package ru.croc.task18.src;

import java.sql.*;
import ru.croc.task18.src.Exceptions.ProductDontExists;

public class ProductDataBaseDao {
    String connectionUrl = "jdbc:h2:/DataBaseFiles/ShopDataBase1";
    static final String user = "sa";
    static final String password = "";
    public Product findProduct(String productCode) throws SQLException {
        /*Поиск в базе данных товара с указанным артикулом.
        Если соответствующего товара в базе данных нет, метод возвращает null.
        * */
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password))
        {
            try (Statement statement = conn.createStatement()){
                try (ResultSet result = statement.executeQuery("SELECT * FROM ITEM " +
                        "WHERE articleNumber = " + "'"+productCode+"'")) {
                    if (result.next()) {
                        return new Product(result.getString("nameGoods"),result.getString("articleNumber"),result.getInt("price"));
                    }
                    else {
                        return null;
                    }
                }
            }
        }
    }
    public static void update(Connection connection, String tableName, String first, String second, String third) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("INSERT INTO " + tableName +  " VALUES" +
                    "(" + first + ", " + second + ", " + third+")");
        }
    }

    public Product createProduct(Product product) throws Exceptions.ProductExistsCodeException {
    /*Создание нового товара. Если в базе данных существует товар с переданным артикулом,
     метод выбрасывает исключение.
     */
        Product foundProduct=null;
        try {
            foundProduct = findProduct(product.getArticleNumber());
            if(foundProduct!=null) {
                throw new Exceptions.ProductExistsCodeException(product.getArticleNumber()+" already exists");
            }else
            {
                try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
                    update(conn,"ITEM","'"+product.getNameGoods()+"'","'"+product.getArticleNumber()+"'","'"+Integer.toString(product.getPrice())+"'");
                    return product;
                }
            }

        } catch (SQLException e) {
            return null;
        }

    }
    public Product updateProduct(Product product) throws Exceptions.ProductDontExists {
    /*Изменение информации о товаре.
    Название и цена товара в базе данных заменяется на значения,
    указанные в полях параметра product. Артикул товара,
    данные которого должны быть изменены, также задается полем объекта product.
     */
        Product foundProduct=null;
        try {
            foundProduct = findProduct(product.getArticleNumber());
            if(foundProduct==null) {
                throw new ProductDontExists(product.getArticleNumber()+" not found or exists");
            }else
            {
                try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
                    try (Statement statement = conn.createStatement()) {
                        statement.execute("update ITEM set namegoods = " + "'"+product.getNameGoods() +"'"+", price = " + "'"+ product.getPrice()+"'" + " where articleNumber =" + "'"+ product.getArticleNumber()+"'");
                    }
                }
            }

        } catch (SQLException e) {
            return null;
        }
        try {
            return findProduct(product.getArticleNumber());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteProduct(String productCode) throws Exceptions.ProductDontExists {
    /*Удаление товара и всех упоминаний о нем в заказах.
    Вас смущает необходимость изменения уже выданных заказов, но заказчик настаивает.
     */
        Product foundProduct=null;
        try {
            foundProduct = findProduct(productCode);
            if(foundProduct==null) {
                throw new ProductDontExists(productCode+" not found or exists");
            }else
            {
                try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
                    try (Statement statement = conn.createStatement()) {
                        statement.execute("delete from ITEM where articleNumber =" + "'"+ productCode+"'");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
