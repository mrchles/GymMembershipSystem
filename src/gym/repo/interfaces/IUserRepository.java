package gym.repo.interfaces;

import gym.models.User;

public interface IUserRepository {
    User login(String username, String password);
}