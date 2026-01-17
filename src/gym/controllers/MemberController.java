package gym.controllers;

import gym.repositories.MemberRepository;

import java.util.List;
import gym.models.Member;

public class MemberController {

    private final MemberRepository repo;

    public MemberController(MemberRepository repo) {
        this.repo = repo;
    }

    public void addMember(String name, String type, int months) {
        repo.addMember(name, type, months);
    }

    public List<Member> getAllMembers() {
        return repo.getAllMembers();
    }

    public void showAll() {
        List<Member> members = repo.getAllMembers();
        System.out.println("Members:");
        for (Member m : members) {
            System.out.println(
                    m.id + ". " + m.fullName +
                            " | " + m.type +
                            " | months: " + m.months +
                            " | price: " + m.price +
                            " | trainer_id: " + m.trainerId
            );
        }
    }

    public void assignTrainer(int memberId, int trainerId) {
        repo.assignTrainer(memberId, trainerId);
    }
}
