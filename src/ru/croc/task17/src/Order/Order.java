package Order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String numberOrder, name;
    List<String> articles = new ArrayList<>();

    public Order(String numberOrder, String name, String article) {
        this.numberOrder = numberOrder;
        this.name = name;
        this.articles.add(article);
    }

    public String getArticle() {
        String res = "";
        for (var article : articles) {
            res += article + " ";
        }
        return res.substring(0, res.length() - 1);
    }

    public void addArticle(String article) {
        this.articles.add(article);
    }

    public String getName() {
        return name;
    }

    public String getNumberOrder() {
        return numberOrder;
    }
}
