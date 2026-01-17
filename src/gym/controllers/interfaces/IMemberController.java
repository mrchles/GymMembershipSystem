package gym.controllers.interfaces;

public interface IMemberController {
    void addMember(String name, String type, int months,Boolean active);
    void showMembers();
    void showActiveMemberships();
}
