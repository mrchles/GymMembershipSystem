package gym.models;

public class Member {
    private int id;
    private String name;
    private String type;
    private int months;
    private double price;
    private boolean active;

    public Member(int id, String name, String type, int months, double price, boolean active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.months = months;
        this.price = price;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return id + ". " + name + " | " + type + " | " + months + " months | " + price + " | Active: " + active;
    }
}