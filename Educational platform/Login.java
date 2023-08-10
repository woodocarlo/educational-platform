import java.io.*;
import java.util.*;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        while (!exitRequested) {

            System.out.println("\033[33m\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t|                      \033[0m\033[1mLogin Page\033[0m\033[33m                   |");
            System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t|              Please select an option              |");
            System.out.println("\t\t\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t\t\t|                \033[32mPress 1: Admin Login\033[0m\033[33m               |");
            System.out.println("\t\t\t\t\t\t|                \033[32mPress 2: Student Login\033[0m\033[33m             |");
            System.out.println("\t\t\t\t\t\t|                \033[32mPress 0: Exit \033[0m\033[33m                     |");
            System.out.println("\t\t\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.print("\033[33mEnter your choice:\033[0m ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\t\t\t\t\t\t \033[31m Invalid choice. Please enter a number.\033[0m ");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAdminLogin(scanner);
                    break;

                case 2:
                    handleStudentLogin(scanner);
                    break;
                case 0:
                    exitRequested = true;
                    return;
                default:
                    System.out.println("\t\t\t\t\t\t\t \033[31m Invalid choice. Please enter a valid option.\033[0m ");
            }
        }
    }

    private static void handleAdminLogin(Scanner scanner) {
        System.out.print("\t\t\t\t\t\t\t\t\033[33m Enter username:\033[0m  ");
        String username = scanner.nextLine();
        System.out.print("\t\t\t\t\t\t\t\t\033[33m Enter password:\033[0m  ");
        String password = scanner.nextLine();

        if (username.equals("admin")) {
            if (isAdminPasswordCorrect(password)) {
                System.out.println("\t\t\t\t\t\t\t\t\033[32m Admin login successful!\033[0m ");
                AdministrationPage administrationPage = new AdministrationPage();
                administrationPage.displayPage(); // Display the administration page
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\033[31m Invalid password for admin.\033[0m ");
            }
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\033[31m Username does not exist.\033[0m ");
        }
    }
    
    private static void handleStudentLogin(Scanner scanner) {
        System.out.print("\t\t\t\t\t\t\t\t\033[33m Enter username:\033[0m ");
        String username = scanner.nextLine();
        System.out.print("\t\t\t\t\t\t\t\t\033[33m Enter password: \033[0m");
        String password = scanner.nextLine();

        if (isStudentUsernameExists(username)) {
            if (isStudentPasswordCorrect(username, password)) {
                System.out.println("\t\t\t\t\t\t\t\t \033[32m Student login successful!\033[0m");
                Student student = new Student(username, password);  // Create a Student object with provided credentials
                student.run();  // Run the student's content
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\033[31m Invalid password for student.\033[0m");
            }
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\033[31m Username does not exist.\033[0m");
        }
    }
    private static boolean isAdminPasswordCorrect(String password) {
        try (Scanner scanner = new Scanner(new File("admin.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("password:") && line.substring(9).trim().equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isStudentUsernameExists(String username) {
        File directory = new File(".");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt") && file.getName().equals(username + ".txt")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isStudentPasswordCorrect(String username, String password) {
        try (Scanner scanner = new Scanner(new File(username + ".txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("password:") && line.substring(9).trim().equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}

