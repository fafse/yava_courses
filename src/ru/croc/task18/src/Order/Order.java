package Order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String name;
    private List<String> articleNumber = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getArticleNumber() {
        String res = "";
        for(var articleNumber:articleNumber)
        {
            res+=articleNumber+" ";
        }

        return res.substring(0,res.length()-1);
    }

    public Order(String name,
                 List<String> articleNumber) {
        this.name = name;
        this.articleNumber.addAll(articleNumber);
    }


}
