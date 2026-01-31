package gym.controllers;

import gym.controllers.interfaces.ITrainerController;
import gym.models.Role;
import gym.models.Trainers;
import gym.models.User;
import gym.repo.MemberRepository;
import gym.repo.TrainerRepository;

import java.util.List;
import java.util.Scanner;

public class TrainerController implements ITrainerController {

    private final TrainerRepository trainerRepo;
    private final MemberRepository memberRepo;

    public TrainerController(TrainerRepository trainerRepo, MemberRepository memberRepo) {
        this.trainerRepo = trainerRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public List<Trainers> getAllTrainers(User user) {
        return trainerRepo.getAllTrainers();
    }

    @Override
    public void chooseTrainer(User user) {
        if (user.getRole() != Role.ADMIN) {
            System.out.println("Access denied! Only ADMIN can assign trainers.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        List<Trainers> trainers = trainerRepo.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No trainers available");
            return;
        }

        System.out.println("Available trainers:");
        for (Trainers t : trainers) {
            System.out.println(t.getId() + ". " + t.getName() + " (" + t.getSpecialization() + ")");
        }

        System.out.print("Enter member ID: ");
        int memberId = sc.nextInt();

        System.out.print("Enter trainer ID: ");
        int trainerId = sc.nextInt();

        memberRepo.assignTrainer(memberId, trainerId);
        System.out.println("Trainer assigned successfully");
    }
}
