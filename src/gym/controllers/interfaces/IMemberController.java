package gym.controllers.interfaces;

public interface IMemberController {
    void addMember(String name, String type, int months);
    void showMembers();
    void deleteMember(int id);
}
