package Product;

import Exceptions.*;

import java.sql.*;

public class ProductDataBaseDao {
    private final String connectionUrl;
    private final String user;
    private final String password;
    private final Connection connection;
    private final Statement statement;

    public ProductDataBaseDao(String connectionUrl,
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

    public Product findProduct(String articleNumber) throws SQLException {
        /*Поиск в базе данных товара с указанным артикулом.
        Если соответствующего товара в базе данных нет, метод возвращает null.
        * */
        try (ResultSet result = statement.executeQuery("SELECT * FROM ITEM " +
                "WHERE articleNumber = " +
                "'" + articleNumber + "'")) {
            if (result.next()) {
                return new Product(result.getString("nameGoods"),
                        result.getString("articleNumber"),
                        result.getInt("price"));
            } else {
                return null;
            }
        }
    }

    private void update(String tableName,
                        String first,
                        String second,
                        String third) throws SQLException {

        statement.execute("INSERT INTO " + tableName + " VALUES" + "(" +
                first + ", " +
                second + ", " +
                third + ")");
    }

    public Product createProduct(Product product) throws Exceptions.ProductExistsCodeException {
    /*Создание нового товара. Если в базе данных существует товар с переданным артикулом,
     метод выбрасывает исключение.
     */
        Product foundProduct = null;
        try {
            update("ITEM", "'" + product.getNameGoods() + "'",
                    "'" + product.getArticleNumber() + "'",
                    "'" + Integer.toString(product.getPrice()) + "'");
            return product;
        } catch (SQLException e) {
            return null;
        }

    }

    public Product updateProduct(Product product) throws ProductDontExistsException {
    /*Изменение информации о товаре.
    Название и цена товара в базе данных заменяется на значения,
    указанные в полях параметра product. Артикул товара,
    данные которого должны быть изменены, также задается полем объекта product.
     */
        Product foundProduct = null;
        try {
            foundProduct = findProduct(product.getArticleNumber());
            if (foundProduct == null) {
                throw new ProductDontExistsException(product.getArticleNumber() + " not found or not exists");
            } else {
                statement.execute("update ITEM set namegoods = " +
                        "'" + product.getNameGoods() + "'" + ", price = " + "'" +
                        product.getPrice() + "'" + " where articleNumber =" + "'" +
                        product.getArticleNumber() + "'");
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

    public void deleteProduct(String productCode) throws ProductDontExistsException {
    /*Удаление товара и всех упоминаний о нем в заказах.
    Вас смущает необходимость изменения уже выданных заказов, но заказчик настаивает.
     */
        Product foundProduct = null;
        try {
            statement.execute("delete from ITEM where articleNumber =" + "'" + productCode + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
