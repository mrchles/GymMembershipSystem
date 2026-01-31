package gym.repo.interfaces;

import gym.models.Member;

import java.util.List;

public interface IMemberRepository {
    void addMember(String name, String type, int months, double price, boolean active);
    List<Member> getAllMembers();
    List<Member> getActiveMembers();
    boolean deleteMember(int id);
    void assignTrainer(int memberId, int trainerId);
}