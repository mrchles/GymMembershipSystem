package gym.controllers;

import gym.controllers.interfaces.IMemberController;
import gym.models.Subscription;
import gym.repo.interfaces.IMemberRepository;
import gym.models.Member;
import java.util.List;

public class MemberController implements IMemberController {

    private final IMemberRepository repository;

    public MemberController(IMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMember(String name, int subscriptionId, int months, double price, boolean active) {
        Subscription sub = repository.getSubscriptionById(subscriptionId);
        if (sub == null) {
            System.out.println("Subscription not found!");
            return;
        }

        price = sub.getPricePerMonth() * months;

        repository.addMember(name, subscriptionId, months, price, active);;
        System.out.println("member added successfully");
    }

    @Override
    public void showMembers() {
        List<Member> members = repository.getAllMembers();
        members.forEach(System.out::println);
    }

    @Override
    public void showActiveMemberships() {
        List<Member> members = repository.getActiveMembers();
        members.forEach(System.out::println);
    };

    @Override
    public void deleteMember(int id) {
        if (repository.deleteMember(id)) System.out.println("member successfully deleted");
        else System.out.println("member is not exist");
  }
}
