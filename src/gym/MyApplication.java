package gym;

import gym.controllers.interfaces.IMemberController;
import java.util.Scanner;
import java.util.List;
import gym.controllers.TrainerController;
import gym.models.Trainers;

public class MyApplication {

    private final IMemberController controller;
    private final TrainerController trainerController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IMemberController controller,
                         TrainerController trainerController) {
        this.controller = controller;
        this.trainerController = trainerController;
    }

    public void start() {
        boolean run = true;

        while (run) {
            System.out.println("gym fitness membership system");
            System.out.println("select option:");
            System.out.println("1. add member");
            System.out.println("2. show all members");
            System.out.println("3. show active memberships");
            System.out.println("4. delete members");
            System.out.println("5. show all trainers:");
            System.out.println("6. choose trainer:");
            System.out.println("0. exit");
            System.out.print("enter: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> controller.showMembers();
                case 3 -> controller.showActiveMemberships();
                case 4 -> deleteMember();
                case 5 -> showAllTrainers();
                case 6 -> trainerController.chooseTrainer();
                case 0 -> run = false;
                default -> System.out.println("wrong option");
            }
        }
    }

    private void addMember() {
        System.out.print("full name: ");
        String name = scanner.nextLine();
        System.out.print("type (standart,premium or VIP): ");
        String type = scanner.nextLine();
        System.out.print("months: ");
        int months = scanner.nextInt();
        scanner.nextLine();
        boolean active = months > 0;
        controller.addMember(name, type, months, active);
        System.out.println("member added");
    }

    private void deleteMember() {
        System.out.print("Enter member ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        controller.deleteMember(id);
    }
    private void showAllTrainers() {
        List<Trainers> trainers = trainerController.getAllTrainers();
        System.out.println("Trainers:");
        for (Trainers t : trainers) {
            System.out.println(t.getId() + ". " + t.getName() + " (" + t.getSpecialization() + ")");
        }
    }
}
