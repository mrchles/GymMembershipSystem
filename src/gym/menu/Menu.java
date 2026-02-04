package gym.menu;

import gym.models.User;

public class Menu {

    private Menu() {

    }
    public static void Menu(User user) {
        System.out.println("GYM fitness database system");

        switch (user.getRole()) {
            case ADMIN   -> AdminMenu();
            case EDITOR  -> EditorMenu();
            case MEMBER  -> MemberMenu();
        }

    }

    private static void AdminMenu() {
        System.out.println("  1. add member");
        System.out.println("  2. show all members");
        System.out.println("  3. show active memberships");
        System.out.println("  4. delete member");
        System.out.println("  5. show all trainers");
        System.out.println("  6. assign trainer to member");
        System.out.println("  7. search member by name");
        System.out.println("  8. show available subscriptions");
        System.out.println("  0. Exit");
    }

    private static void EditorMenu() {
        System.out.println("  1. add member");
        System.out.println("  2. show all members");
        System.out.println("  3. show active memberships");
        System.out.println("  4. delete member");
        System.out.println("  5. show all trainers");
        System.out.println("  6. assign trainer to member");
        System.out.println("  7. search member by name");
        System.out.println("  8. show available subscriptions");
        System.out.println("  0. exit");
    }

    private static void MemberMenu() {
        System.out.println("  1. my profile");
        System.out.println("  2. show all trainers");
        System.out.println("  3. search member by name");
        System.out.println("  0. exit");
    }
}
