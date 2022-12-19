package Product;

public class Product {
    private final String nameGoods;
    private final String articleNumber;
    private final int price;

    public String getNameGoods() {
        return nameGoods;
    }

    public int getPrice() {
        return price;
    }

    public String getArticleNumber() {
        return articleNumber;
    }


    public Product(String nameGoods,
                   String articleNumber,
                   int price) {
        this.articleNumber = articleNumber;
        this.nameGoods = nameGoods;
        this.price = price;
    }

    @Override
    public String toString() {
        return articleNumber + " " + nameGoods + " " + price;
    }

}
