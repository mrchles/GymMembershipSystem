package gym.repo.interfaces;

public interface IMemberRepository {
    void addMember(String name, String type, int months);
    void showAll();
    void deleteMember(int id);

    void assignTrainer(int memberId, int trainerId);
}
