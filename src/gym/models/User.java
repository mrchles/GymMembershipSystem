package gym.models;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;
    private final int memberId;

    public User(int id, String username, String password, Role role,int memberId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.memberId = memberId;
    }

    public Role getRole() {return role;}
    public String getUsername() {return username;}
    public int getMemberId() {
        return memberId;
    }
}
