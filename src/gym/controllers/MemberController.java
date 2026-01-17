package gym.controllers;

import gym.controllers.interfaces.IMemberController;
import gym.repo.interfaces.IMemberRepository;

public class MemberController implements IMemberController {

    private final IMemberRepository repository;

    public MemberController(IMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMember(String name, String type, int months) {
        repository.addMember(name, type, months);
    }

    @Override
    public void showMembers() {
        repository.showAll();
    }
    @Override
    public void deleteMember(int id) {
        repository.deleteMember(id);
    }
}
