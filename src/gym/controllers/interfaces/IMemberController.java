package gym.controllers.interfaces;

import gym.models.User;


public interface IMemberController {
    void addMember(User user, String name, int subscriptionId, int months, double price, boolean active);
    void showMembers(User user);
    void showActiveMemberships(User user);
    void deleteMember(User user,int id);
    void showMyProfile(User user);
    void findMemberByName(User user, String name);
    void showSubscriptions();
}
