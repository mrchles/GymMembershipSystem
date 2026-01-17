package gym;

import gym.controllers.MemberController;
import gym.controllers.TrainerController;
import gym.models.Member;
import gym.models.Trainer;

import java.util.List;
import java.util.Scanner;

public class MyApplication {

    private final MemberController memberController;
    private final TrainerController trainerController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(MemberController memberController, TrainerController trainerController) {
        this.memberController = memberController;
        this.trainerController = trainerController;
    }

    public void start() {
        boolean run = true;

        while (run) {
            System.out.println("=== GYM FITNESS SYSTEM ===");
            System.out.println("1.ADD A MEMBER");
            System.out.println("2. ПОКАЗАТЬ ВСЕХ УЧАСТНИКОВ");
            System.out.println("3. ПОКАЗАТЬ ВСЕХ АКТИВНЫХ УЧАСТНИКОВ");
            System.out.println("4. Delete member");
            System.out.println("5. Show members");
            System.out.println("0. ВЫХОД");
            System.out.print("ВВОД: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> controller.showMembers();
                case 4 -> deleteMember();
                case 2 -> memberController.showAll();
                case 3 -> showAllTrainers();
                case 4 -> trainerController.chooseTrainer();
                case 0 -> run = false;
                default -> System.out.println("Wrong option");


            }
        }
    }

    private void addMember() {
        System.out.print("Full name: ");
        String name = scanner.nextLine();
        System.out.print("Type (STANDARD / PREMIUM): ");
        String type = scanner.nextLine();
        System.out.print("Months: ");
        int months = scanner.nextInt();
        scanner.nextLine();

        memberController.addMember(name, type, months);
        System.out.println("Member added!");
    }

    private void showAllTrainers() {
        List<Trainer> trainers = trainerController.getAllTrainers();
        System.out.println("Trainers:");
        for (Trainer t : trainers) {
            System.out.println(t.id + ". " + t.name + " (" + t.specialization + ")");
        }
    }

    private void deleteMember() {
        System.out.print("Enter member ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        controller.deleteMember(id);
    }
}
