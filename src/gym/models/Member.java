package gym.models;

public class Member {
    public int id;
    public String fullName;
    public String type;
    public int months;
    public double price;
    public Integer trainerId; // теперь Integer, чтобы можно было хранить null

    public Member(int id, String fullName, String type, int months, double price, Integer trainerId) {
        this.id = id;
        this.fullName = fullName;
        this.type = type;
        this.months = months;
        this.price = price;
        this.trainerId = trainerId;
    }
}
