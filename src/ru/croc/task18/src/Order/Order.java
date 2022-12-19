package Order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String name;
    private List<String> articleNumber = new ArrayList<>();

    public Order(String name,
                 List<String> articleNumber) {
        this.name = name;
        this.articleNumber.addAll(articleNumber);
    }


}
