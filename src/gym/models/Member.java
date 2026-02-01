package gym.models;

public class Member {
    private final int id;
    private final String fullName;
    private final int months;
    private final double price;
    private final boolean active;
    private final TrainerInfo trainer;
    private final Subscription subscription;

    private Member(Builder b) {
        this.id = b.id;
        this.fullName = b.fullName;
        this.months = b.months;
        this.price = b.price;
        this.active = b.active;
        this.trainer = b.trainer;
        this.subscription = b.subscription;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public int getMonths() { return months; }
    public double getPrice() { return price; }
    public boolean isActive() { return active; }
    public TrainerInfo getTrainer() { return trainer; }
    public Subscription getSubscription() { return subscription; }


    @Override
    public String toString() {
        return id + ". " + fullName +
                " | " + subscription +
                " | months:" + months +
                " | price:" + price +
                " | active:" + (active ? "yes" : "no") +
                " | trainer:" + (trainer != null ? trainer : "none");
    }

    public static class Builder {
        private int id;
        private String fullName;
        private int months;
        private double price;
        private boolean active;
        private TrainerInfo trainer;
        private Subscription subscription;

        public Builder id(int id) { this.id = id; return this; }
        public Builder fullName(String fullName) { this.fullName = fullName; return this; }
        public Builder months(int months) { this.months = months; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder active(boolean active) { this.active = active; return this; }
        public Builder trainer(TrainerInfo trainer) { this.trainer = trainer; return this; }
        public Builder subscription(Subscription subscription) { this.subscription = subscription; return this; }
        public Member build() { return new Member(this); }
    }

    public static class TrainerInfo {
        private final int id;
        private final String name;
        private final String specialization;

        public TrainerInfo(int id, String name, String specialization) {
            this.id = id;
            this.name = name;
            this.specialization = specialization;
        }

        @Override
        public String toString() {
            return name + " (" + specialization + ")";
        }
    }
}
