package gym.controllers;

import gym.models.Trainer;
import gym.repositories.MemberRepository;
import gym.repositories.TrainerRepository;

import java.util.List;
import java.util.Scanner;

public class TrainerController {

    private final TrainerRepository trainerRepo;
    private final MemberRepository memberRepo;

    public TrainerController(TrainerRepository trainerRepo, MemberRepository memberRepo) {
        this.trainerRepo = trainerRepo;
        this.memberRepo = memberRepo;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepo.getAllTrainers();
    }

    public void chooseTrainer() {
        Scanner sc = new Scanner(System.in);

        List<Trainer> trainers = trainerRepo.getAllTrainers();
        System.out.println("Available trainers:");
        for (Trainer t : trainers) {
            System.out.println(t.id + ". " + t.name + " (" + t.specialization + ")");
        }

        System.out.print("Enter member ID: ");
        int memberId = sc.nextInt();
        sc.nextLine();

        System.out.print("Choose trainer ID: ");
        int trainerId = sc.nextInt();
        sc.nextLine();

        memberRepo.assignTrainer(memberId, trainerId);
        System.out.println("Trainer assigned successfully!");
    }
}
