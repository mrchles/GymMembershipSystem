package gym;

import gym.controllers.interfaces.IMemberController;

import java.util.Scanner;

public class MyApplication {

    private final IMemberController controller;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IMemberController controller) {
        this.controller = controller;
    }

    public void start() {
        boolean run = true;

        while (run) {
            System.out.println("gym fitness membership system");
            System.out.println("1. add member");
            System.out.println("2. show all members");
            System.out.println("3. show active memberships");
            System.out.println("4. choose coaches");
            System.out.println("5. deactivate membership for user");
            System.out.println("0. exit");
            System.out.print("enter: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> controller.showMembers();
                case 3 -> controller.showActiveMemberships();
                case 0 -> run = false;
                default -> System.out.println("wrong option");
            }
        }
    }

    private void addMember() {
        System.out.print("full name: ");
        String name = scanner.nextLine();
        System.out.print("type (STANDARD / PREMIUM): ");
        String type = scanner.nextLine();
        System.out.print("months: ");
        int months = scanner.nextInt();
        scanner.nextLine();
        System.out.print("active?: ");
        scanner.nextLine();
        boolean active = Boolean.parseBoolean(scanner.nextLine());
        controller.addMember(name, type, months, active);
        System.out.println("member added");
    }
}
