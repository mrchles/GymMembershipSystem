package gym.repo.interfaces;

public interface IMemberRepository {
    void addMember(String name, String type, int months,Boolean active);
    void showAll();
    void showActiveMemberships();
    void deleteMember(int id);
    void assignTrainer(int memberId, int trainerId);
}
