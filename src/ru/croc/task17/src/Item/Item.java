package Item;

public class Item {
    private String article,name;
    private int price;
    public Item(String article, String name, int price)
    {
        this.article=article;
        this.name= name;
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }
}
