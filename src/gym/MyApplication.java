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
            System.out.println("\n=== GYM FITNESS SYSTEM ===");
            System.out.println("1. ДОБАВИТЬ УЧАСТНИКА");
            System.out.println("2. ПОКАЗАТЬ ВСЕХ УЧАСТНИКОВ");
            System.out.println("3. ПОКАЗАТЬ ВСЕХ АКТИВНЫХ УЧАСТНИКОВ");
            System.out.println("4. Show members");
            System.out.println("5. Show members");
            System.out.println("0. ВЫХОД");
            System.out.print("ВВОД: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> controller.showMembers();
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
        System.out.print("Active?: ");
        String active = scanner.nextLine();

        controller.addMember(name, type, months);
        System.out.println("Member added!");
    }
}
