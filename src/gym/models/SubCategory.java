package gym.models;

public enum SubCategory {
    base("base"),
    premium("premium"),
    elite("elite");

    private final String displayName;

    SubCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}