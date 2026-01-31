package gym.controllers;

import gym.controllers.interfaces.IMemberController;
import gym.models.Subscription;
import gym.repo.interfaces.IMemberRepository;
import gym.models.Member;
import gym.models.Role;
import gym.models.User;
import gym.repo.interfaces.IMemberRepository;

import java.util.List;

public class MemberController implements IMemberController {

    private final IMemberRepository repository;

    public MemberController(IMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMember(String name, int subscriptionId, int months, double price, boolean active) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty");
            return;
        }

        if (months <= 0) {
            System.out.println("Months must be greater than 0");
            return;
        }

        Subscription sub = repository.getSubscriptionById(subscriptionId);
        if (sub == null) {
            System.out.println("Subscription not found!");
            return;
        }


        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty");
            return;
        }

        if (months <= 0) {
            System.out.println("Months must be greater than 0");
            return;
        }

        price = sub.getPricePerMonth() * months;

        repository.addMember(name, subscriptionId, months, price, active);;
        System.out.println("member added successfully");
    }

    public void showMembers(User user) {
        if (user.getRole() == Role.MEMBER) {
            System.out.println("Access denied! MEMBERS cannot view full member list.");
            return;
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

        if (id <= 0) {
            System.out.println("Invalid member ID");
            return;
        }

        if (repository.deleteMember(id)) {
            System.out.println("Member successfully deleted");
        } else {
            System.out.println("Member not found");
        }
    }
}
