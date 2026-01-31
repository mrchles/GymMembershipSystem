package gym;

import gym.controllers.MemberController;
import gym.controllers.TrainerController;
import gym.models.Trainers;
import gym.models.User;

import java.util.List;
import java.util.Scanner;

public class MyApplication {

    private final MemberController memberController;
    private final TrainerController trainerController;
    private final User currentUser;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(MemberController memberController,
                         TrainerController trainerController,
                         User currentUser) {
        this.memberController = memberController;
        this.trainerController = trainerController;
        this.currentUser = currentUser;
    }

    public void start() {
        boolean run = true;

        while (run) {
            System.out.println("\nGym Fitness Membership System");
            System.out.println("1. Add member");
            System.out.println("2. Show all members");
            System.out.println("3. Show active memberships");
            System.out.println("4. Delete member");
            System.out.println("5. Show all trainers");
            System.out.println("6. Assign trainer to member");
            System.out.println("0. Exit");
            System.out.print("Enter: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> memberController.addMember(currentUser,
                        prompt("Full name: "),
                        prompt("Type (standard, premium, VIP): "),
                        Integer.parseInt(prompt("Months: ")),
                        true);

                case 2 -> memberController.showMembers(currentUser);

                case 3 -> memberController.showActiveMemberships(currentUser);

                case 4 -> memberController.deleteMember(currentUser,
                        Integer.parseInt(prompt("Enter member ID to delete: ")));

                case 5 -> showAllTrainers();

                case 6 -> trainerController.assignTrainer(currentUser);

                case 0 -> run = false;

                default -> System.out.println("Wrong option");
            }
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void showAllTrainers() {
        List<Trainers> trainers = trainerController.getAllTrainers();
        System.out.println("Trainers:");
        for (Trainers t : trainers) {
            System.out.println(t.getId() + ". " + t.getName() + " (" + t.getSpecialization() + ")");
        }
    }
}