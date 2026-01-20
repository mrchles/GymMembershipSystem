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
    public void addMember(String name, String type, int months,boolean active) {
        double price;

        if (type.equalsIgnoreCase("premium")) {
            price = 15000 * months;
        } else if (type.equalsIgnoreCase("VIP")) {
            price = 25000 * months;
        } else { // STANDARD
            price = 10000 * months;
        }
        repository.addMember(name, type, months,price,active);
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
