package gym.controllers.interfaces;

import gym.models.Trainers;
import gym.models.User;

import java.util.List;

public interface ITrainerController {
    List<Trainers> getAllTrainers(User user);
    void chooseTrainer(User user);
}
