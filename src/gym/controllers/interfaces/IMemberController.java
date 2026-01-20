package gym.controllers.interfaces;

import gym.models.Member;
import java.util.List;

public interface IMemberController {
    void addMember(String name, String type, int months,boolean active);
    void showMembers();
    void showActiveMemberships();
    void deleteMember(int id);
}
