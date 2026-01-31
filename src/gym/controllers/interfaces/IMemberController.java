package gym.controllers.interfaces;

import gym.models.Member;
import java.util.List;

public interface IMemberController {
    void addMember(String name, int subscriptionId, int months, double price, boolean active);
    void showMembers();
    void showActiveMemberships();
    void deleteMember(int id);
}
