package gym.controllers;

import gym.controllers.interfaces.IAuthController;
import gym.models.User;
import gym.repo.interfaces.IUserRepository;

public class AuthController implements IAuthController {
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
