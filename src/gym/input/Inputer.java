package gym.input;

import java.util.Scanner;

public class Inputer {

    private Inputer() {}

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                scanner.nextLine();
                System.out.println("Please enter a valid number.");
            }
        }
    }


    public static String readNonEmptyLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isBlank()) {
                return line;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
