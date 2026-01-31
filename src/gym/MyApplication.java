package gym;

import gym.controllers.interfaces.IMemberController;
import java.util.Scanner;
import java.util.List;
import gym.controllers.TrainerController;
import gym.models.Trainers;
import gym.models.User;

public class MyApplication {

    private final IMemberController controller;
    private final TrainerController trainerController;
    private final User currentUser;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IMemberController controller, TrainerController trainerController,User currentUser) {
        this.controller = controller;
        this.trainerController = trainerController;
        this.currentUser = currentUser;
    }

    public void start() {
        boolean run = true;

        while (run) {
            System.out.println("gym fitness membership system:");
            System.out.println("select option:");
            System.out.println("1. add member");
            System.out.println("2. show all members");
            System.out.println("3. show active memberships");
            System.out.println("4. delete members");
            System.out.println("5. show all trainers:");
            System.out.println("6. choose trainers:");
            System.out.println("0. exit");
            System.out.print("enter: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.nextLine();
            }
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> controller.showMembers(currentUser);
                case 3 -> controller.showActiveMemberships(currentUser);
                case 4 -> deleteMember(currentUser);
                case 5 -> showAllTrainers();
                case 6 -> trainerController.chooseTrainer(currentUser);
                case 0 -> run = false;
                default -> System.out.println("wrong option");
            }
        }
    }

    private void addMember() {
        System.out.print("full name: ");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            System.out.println("wrong option");
            return;
        }

        int subscriptionId = 0;
        while (true) {
            System.out.println("Choose membership type:");
            System.out.println("1 - STANDARD");
            System.out.println("2 - PREMIUM");
            System.out.println("3 - VIP");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();
            if (choice.equals("1")) subscriptionId = 1;
            else if (choice.equals("2")) subscriptionId = 2;
            else if (choice.equals("3")) subscriptionId = 3;
            else {
                System.out.println("Wrong option");
                continue;
            }
            break;
        }

        int months = 0;
        while (true) {
            System.out.print("Enter months (1-12): ");
            if (scanner.hasNextInt()) {
                months = scanner.nextInt();
                scanner.nextLine();
                if (months > 0 && months <= 12) break;
            } else {
                scanner.nextLine();
            }
            System.out.println("wrong option");
        }
        boolean active = months > 0;
        controller.addMember(currentUser,name,subscriptionId, months, 0, active);
        System.out.println("member added");
    }

    private void deleteMember(User currentUser) {
        int id;
        while (true) {
            System.out.print("Enter member ID to delete: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                if (id > 0) break;
            } else {
                scanner.nextLine();
            }
            System.out.println("wrong option");
        }
        controller.deleteMember(this.currentUser,id);
    }
    private void showAllTrainers() {
        List<Trainers> trainers = trainerController.getAllTrainers(currentUser);
        System.out.println("Trainers:");
        for (Trainers t : trainers) {
            System.out.println(t.getId() + ". " + t.getName() + " (" + t.getSpecialization() + ")");
        }
    }
}
