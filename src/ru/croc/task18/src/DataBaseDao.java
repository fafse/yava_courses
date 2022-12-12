package ru.croc.task18.src;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseDao {
    String connectionUrl = "jdbc:h2:/DataBaseFiles/ShopDataBase10";
    static final String user = "sa";
    static final String password = "";
    Product findProduct(String productCode)
    {
        /*Поиск в базе данных товара с указанным артикулом.
        Если соответствующего товара в базе данных нет, метод возвращает null.
        * */
        Product product = null;
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password))
        {
            if ()
        }

        return product;
    }
    Product createProduct(Product product);
    /*Создание нового товара. Если в базе данных существует товар с переданным артикулом,
     метод выбрасывает исключение.
     */
    Product updateProduct(Product product);
    /*Изменение информации о товаре.
    Название и цена товара в базе данных заменяется на значения,
    указанные в полях параметра product. Артикул товара,
    данные которого должны быть изменены, также задается полем объекта product.
     */
    void deleteProduct(String productCode);
    /*Удаление товара и всех упоминаний о нем в заказах.
    Вас смущает необходимость изменения уже выданных заказов, но заказчик настаивает.
     */
    Order createOrder(String userLogin, List<Product> products);
    /*Создание заказа.
    Для указанного пользователя в базе данных создается новый заказ с заданным списком товаров.*/
}
