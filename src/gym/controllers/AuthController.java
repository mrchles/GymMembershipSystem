package gym.controllers;

import gym.models.User;
import gym.repo.interfaces.IUserRepository;

public class AuthController {
    private final IUserRepository userRepo;

    public AuthController(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User login(String username, String password) {
        User user = userRepo.login(username, password);
        if (user == null) throw new RuntimeException("Wrong login or password");
        return user;
    }
}
