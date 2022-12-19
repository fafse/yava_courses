package ru.croc.task18.src;

import java.sql.SQLException;

import Exceptions.*;
import Order.*;
import Product.*;

import java.util.LinkedList;
import java.util.List;

public class Task18 {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:h2:/DataBaseFiles/ShopDataBase10";
        ProductDataBaseDao productDataBaseDao = new ProductDataBaseDao(connectionUrl,
                "sa",
                "");
        try {
            System.out.println("I try found T8 and get " +
                    productDataBaseDao.findProduct("Т8"));
            Product wrongProduct = new Product("Монитор",
                    "Т1",
                    500);
            Product rightProduct = new Product("LG",
                    "Т8",
                    50000);
            try {
                System.out.println("I create right " + productDataBaseDao.createProduct(rightProduct));

            } catch (ProductExistsCodeException e) {
                System.out.println(e);
            }
            try {
                rightProduct = new Product("NOKIA",
                        "Т8",
                        500000);
                System.out.println("I updated " + productDataBaseDao.updateProduct(rightProduct));
            } catch (ProductDontExistsException e) {
                System.out.println(e);
            }
            try {
                System.out.println("I try create wrong" + productDataBaseDao.createProduct(wrongProduct));
            } catch (ProductExistsCodeException e) {
                System.out.println(e);
            }
            productDataBaseDao.deleteProduct("Т8");
            System.out.println("I try find T8 after deleting " + productDataBaseDao.findProduct("Т8"));
            List<Product> products = new LinkedList<Product>();
            products.add(new Product("NOTEBOOK",
                    "Т9",
                    5));
            products.add(new Product("Phone",
                    "Т10",
                    1000));
            OrderDataBaseDao orderDataBaseDao = new OrderDataBaseDao(connectionUrl, "sa", "");
            orderDataBaseDao.createOrder("fafse", products);
            System.out.println(productDataBaseDao.findProduct("Т10"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ProductDontExistsException e) {
            throw new RuntimeException(e);
        }
    }
}
