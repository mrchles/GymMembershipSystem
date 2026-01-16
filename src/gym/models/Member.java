package gym.models;

public class Member {
    private int id;
    private String fullName;
    private String type;
    private int months;
    private double price;
    private boolean active;

    public Member(int id, String fullName, String type, int months, double price) {
        this.id = id;
        this.fullName = fullName;
        this.type = type;
        this.months = months;
        this.price = price;
        this.active = active;
        boolean active = true;
        if (months <= 0) active = false;
    }
}
