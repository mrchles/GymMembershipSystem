package gym.controllers;

import gym.controllers.interfaces.IMemberController;
import gym.repo.interfaces.IMemberRepository;
import gym.models.Member;
import java.util.List;

public class MemberController implements IMemberController {

    private final IMemberRepository repository;

    public MemberController(IMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMember(String name, String type, int months, boolean active) {

        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty");
            return;
        }

        if (months <= 0) {
            System.out.println("Months must be greater than 0");
            return;
        }

        if (!type.equalsIgnoreCase("standard")
                && !type.equalsIgnoreCase("premium")
                && !type.equalsIgnoreCase("vip")) {
            System.out.println("Invalid membership type");
            return;
        }

        double price;

        if (type.equalsIgnoreCase("premium")) {
            price = 15000 * months;
        } else if (type.equalsIgnoreCase("vip")) {
            price = 25000 * months;
        } else {
            price = 10000 * months;
        }

        repository.addMember(name, type, months, price, active);
        System.out.println("Member added successfully");
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
