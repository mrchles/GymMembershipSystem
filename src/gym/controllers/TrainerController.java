package gym.controllers;

import gym.models.Trainers;
import gym.models.Role;
import gym.models.User;
import gym.repo.MemberRepository;
import gym.repo.TrainerRepository;

import java.util.List;
import java.util.Scanner;

public class TrainerController {

    private final TrainerRepository trainerRepo;
    private final MemberRepository memberRepo;

    public TrainerController(TrainerRepository trainerRepo, MemberRepository memberRepo) {
        this.trainerRepo = trainerRepo;
        this.memberRepo = memberRepo;
    }

    public List<Trainers> getAllTrainers() {
        return trainerRepo.getAllTrainers();
    }

    public void addTrainer(User user, String name, String specialization) {
        if (user.getRole() != Role.ADMIN) {
            System.out.println("Access denied! Only ADMIN can add trainers.");
            return;
        }

        trainerRepo.addTrainer(name, specialization);
        System.out.println("Trainer added successfully!");
    }

    public void assignTrainer(User user) {
        if (user.getRole() != Role.ADMIN) {
            System.out.println("Access denied! Only ADMIN can assign trainers.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        List<Trainers> trainers = trainerRepo.getAllTrainers();
        int maxTrainers = Math.min(trainers.size(), 5);
        System.out.println("Available trainers (max 5):");
        for (int i = 0; i < maxTrainers; i++) {
            Trainers t = trainers.get(i);
            System.out.println(t.getId() + ". " + t.getName() + " (" + t.getSpecialization() + ")");
        }

        int memberId;
        while (true) {
            System.out.print("Enter member ID: ");
            if (sc.hasNextInt()) {
                memberId = sc.nextInt();
                sc.nextLine();
                if (memberId > 0) break;
            } else {
                sc.nextLine();
            }
            System.out.println("Invalid ID");
        }

        int trainerId;
        while (true) {
            System.out.print("Choose trainer ID from the list above: ");
            if (sc.hasNextInt()) {
                trainerId = sc.nextInt();
                sc.nextLine();
                boolean valid = false;
                for (int i = 0; i < maxTrainers; i++) {
                    if (trainers.get(i).getId() == trainerId) {
                        valid = true;
                        break;
                    }
                }

                if (valid) break;
            } else {
                sc.nextLine();
            }
            System.out.println("Invalid ID");
        }

        memberRepo.assignTrainer(memberId, trainerId);
        System.out.println("Trainer assigned successfully!");
    }
}