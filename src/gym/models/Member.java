package gym.models;
import gym.models.Subscription;
public class Member {
    private int id;
    private String fullName;
    private int months;
    private double price;
    private boolean active;
    private int trainerId;
    private Subscription subscription;

    public Member(int id, String fullName, int months, double price ,int trainerId,boolean active,Subscription subscription) {
        this.id = id;
        this.fullName = fullName;
        this.months = months;
        this.price = price;
        this.active = active;
        this.trainerId = trainerId;
        this.subscription = subscription;
    }
    public String getFullName() {

    public int getId() {return id;}
    public String getFullName() {return fullName;}
    public int getMonths() {
        return months;
    }
    public double getPrice() {
        return price;
    }
    public boolean isActive() {return active;}
    public int getTrainerId() {
        return trainerId;
    }
    public Subscription getSubscription(){return subscription;}


    @Override
    public String toString() {
        return "member |" +
                "id:" + getId() +
                " | " + getSubscription() +
                ", name:'" + getFullName() + '\'' +
                ", months:" + getMonths() + '\'' +
                ", price:" + getPrice() +'\'' +
                ", active:"+ (isActive() ? "active" : "inactive") +'\'' +
                ", trainerId:" + getTrainerId() +'\'' +
                '|';
    }
}