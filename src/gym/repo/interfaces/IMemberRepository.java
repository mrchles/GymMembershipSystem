package gym.repo.interfaces;
import gym.models.Member;
import gym.models.Subscription;

import java.util.List;

public interface IMemberRepository {
    void addMember(String name, int subscriptionId, int months, double price, boolean active);
    List<Member> getAllMembers();
    List<Member> getActiveMembers();
    boolean deleteMember(int id);
    void assignTrainer(int memberId, int trainerId);
    Subscription getSubscriptionById(int subscriptionId);
    Member getMemberById(int id);
    List<Member> findMembersByName(String name);
    List<Subscription> getAllSubscriptions();
}
