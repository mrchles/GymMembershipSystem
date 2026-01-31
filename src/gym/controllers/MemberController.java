package gym.controllers;

import gym.models.Member;
import gym.models.Role;
import gym.models.User;
import gym.repo.interfaces.IMemberRepository;

import java.util.List;

public class MemberController {

    private final IMemberRepository repository;

    public MemberController(IMemberRepository repository) {
        this.repository = repository;
    }

    // Добавление члена — ADMIN или EDITOR
    public void addMember(User user, String name, String type, int months, boolean active) {
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.EDITOR) {
            System.out.println("Access denied! You cannot add members.");
            return;
        }

        double price;
        if (type.equalsIgnoreCase("premium")) {
            price = 15000 * months;
        } else if (type.equalsIgnoreCase("VIP")) {
            price = 25000 * months;
        } else { // STANDARD
            price = 10000 * months;
        }

        repository.addMember(name, type, months, price, active);
        System.out.println("Member added successfully");
    }

    public void showMembers(User user) {
        if (user.getRole() == Role.MEMBER) {
            System.out.println("Access denied! MEMBERS cannot view full member list.");
            return;
        }

        List<Member> members = repository.getAllMembers();
        members.forEach(System.out::println);
    }

    public void showActiveMemberships(User user) {
        List<Member> members = repository.getActiveMembers();
        members.forEach(System.out::println);
    }

    public void deleteMember(User user, int id) {
        if (user.getRole() != Role.ADMIN) {
            System.out.println("Access denied! Only ADMIN can delete members.");
            return;
        }

        if (repository.deleteMember(id)) {
            System.out.println("Member successfully deleted");
        } else {
            System.out.println("Member does not exist");
        }
    }
}