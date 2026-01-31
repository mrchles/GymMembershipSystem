package gym.models;

public class Subscription {
    private int id;
    private String name;
    private int pricepermonth;

    public Subscription(int id, String name, int pricepermonth) {
        this.id = id;
        this.name = name;
        this.pricepermonth = pricepermonth;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getPricePerMonth() { return pricepermonth; }

    @Override
    public String toString() {
        return name + " (" + pricepermonth + " per month)";
    }
}