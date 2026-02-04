package gym.controllers;

import gym.controllers.interfaces.IMemberController;
import gym.models.Subscription;
import gym.repo.interfaces.IMemberRepository;
import gym.models.Member;
import gym.models.Role;
import gym.models.User;


import java.util.List;

public class MemberController implements IMemberController {

    private final IMemberRepository repository;

    public MemberController(IMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMember(User user,String name, int subscriptionId, int months, double price, boolean active) {
        if (user.getRole() == Role.MEMBER) {
            System.out.println("Access denied!");
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

        Subscription sub = repository.getSubscriptionById(subscriptionId);
        if (sub == null) {
            System.out.println("Subscription not found");
            return;
        }

        price = sub.getPricePerMonth() * months;

        repository.addMember(name, subscriptionId, months, price, active);
        System.out.println("Member added successfully");
    }

    @Override
    public void showMembers(User user) {
        if (user.getRole() == Role.MEMBER) {
            System.out.println("Access denied!");
            return;
        }
        List<Member> members = repository.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        members.forEach(System.out::println);
    }


    @Override
    public void showActiveMemberships(User user) {
        if (user.getRole() == Role.MEMBER) {
            System.out.println("Access denied!");
            return;
        }
        List<Member> members = repository.getActiveMembers();
        if (members.isEmpty()) {
            System.out.println("No active memberships found.");
            return;
        }
        members.forEach(System.out::println);
    };

    @Override
    public void deleteMember(User user,int id) {
        if (user.getRole() == Role.MEMBER) {
            System.out.println("Access denied!");
            return;
        }

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

    @Override
    public void showMyProfile(User user) {
        if (user.getRole() == Role.MEMBER) {

            if (user.getMemberId() <= 0) {
                System.out.println("Your account is not linked to a member profile.");
                return;
            }
            Member member = repository.getMemberById(user.getMemberId());
            if (member == null) {
                System.out.println("Member profile not found.");
            } else {
                System.out.println(member);
            }
        } else {

            System.out.println("Use 'Show all members' to view all profiles.");
        }
    }

    @Override
    public void findMemberByName(User user, String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Search name cannot be empty.");
            return;
        }

        List<Member> results = repository.findMembersByName(name);

        if (results.isEmpty()) {
            System.out.println("No members found with name: " + name);
            return;
        }

        if (user.getRole() == Role.MEMBER) {

            results.stream()
                    .filter(m -> m.getId() == user.getMemberId())
                    .forEach(System.out::println);

            if (results.stream().noneMatch(m -> m.getId() == user.getMemberId())) {
                System.out.println("No results found for your profile.");
            }
        } else {

            System.out.println("Found " + results.size() + " member(s):");
            results.forEach(System.out::println);
        }
    }

    @Override
    public void showSubscriptions() {
        List<Subscription> subscriptions = repository.getAllSubscriptions();
        if (subscriptions.isEmpty()) {
            System.out.println("No subscriptions available.");
            return;
        }
        System.out.println("Available subscriptions:");
        subscriptions.forEach(System.out::println);
    }

}


