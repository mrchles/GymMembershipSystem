package gym.models;
public class Subscription {
    private int id;
    private String name;
    private int pricepermonth;
    private SubCategory category;

    public Subscription(int id, String name, int pricepermonth,SubCategory category) {
        this.id = id;
        this.name = name;
        this.pricepermonth = pricepermonth;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getPricePerMonth() { return pricepermonth; }
    public SubCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name +" (" + category.getDisplayName() + ") (" + pricepermonth + " per month)";
    }
}