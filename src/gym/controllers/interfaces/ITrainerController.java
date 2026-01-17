package gym.controllers.interfaces;

import gym.models.Trainers;
import java.util.List;

public interface ITrainerController {
    List<Trainers> getAllTrainers();
    void chooseTrainer();
}
