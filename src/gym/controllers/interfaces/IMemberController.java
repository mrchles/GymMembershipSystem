package gym.controllers.interfaces;

import gym.models.Member;
import gym.models.User;

import java.util.List;

public interface IMemberController {
    void addMember(User user, String name, int subscriptionId, int months, double price, boolean active);
    void showMembers(User user);
    void showActiveMemberships(User user);
    void deleteMember(User user,int id);
}
