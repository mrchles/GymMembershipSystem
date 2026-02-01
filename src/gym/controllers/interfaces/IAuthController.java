package gym.controllers.interfaces;

import gym.models.User;

public interface IAuthController {
    User login(String username, String password);
}
