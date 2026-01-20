package gym.models;

public class Member {
    private int id;
    private String fullName;
    private String type;
    private int months;
    private double price;
    private boolean active;
    private int trainerId;

    public Member(int id, String fullName, String type, int months, double price ,int trainerId,boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.type = type;
        this.months = months;
        this.price = price;
        this.active = active;
        this.trainerId = trainerId;
    }

    public int getId() {
        return id;
    }
    public String getFullName() {

        return fullName;
    }
    public String getType(){

        return type;
    }
    public int getMonths() {
        return months;
    }
    public double getPrice() {
        return price;
    }
    public boolean isActive() {
        return active;
    }
    public int getTrainerId() {
        return trainerId;
    }


    @Override
    public String toString() {
        return "member |" +
                "id:" + getId() +
                ", name:'" + getFullName() + '\'' +
                ", type:" + getType() + '\'' +
                ", months:" + getMonths() + '\'' +
                ", price:" + getPrice() +'\'' +
                ", active:"+ (isActive() ? "active" : "inactive") +'\'' +
                ", trainerId:" + getTrainerId() +'\'' +
                '|';
    }


 }



