import java.io.*;
import java.util.*;


public class AdministrationPage {
    public void displayPage () {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("\033[33m\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t|        \033[0m\033[1mWelcome to the Administration Page!\033[0m\033[33m        |");
            System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t|              Please select an option              |");
            System.out.println("\t\t\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t\t\t|           \033[32mPress 1: Create a course\033[0m\033[33m                |");
            System.out.println("\t\t\t\t\t\t|           \033[32mPress 2: Create a new quiz\033[0m\033[33m              |");
            System.out.println("\t\t\t\t\t\t|           \033[32mPress 3: Join a group discussion\033[0m\033[33m        |");
            System.out.println("\t\t\t\t\t\t|           \033[32mPress 0: Exit                   \033[0m\033[33m        |");
            System.out.println("\t\t\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.print("\033[33mEnter your choice:\033[0m ");
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character from the input buffer

                switch (choice) {
                    case 1:
                        CoursePortal coursePortal=new CoursePortal();
                        coursePortal.Courseportal();
                        break;
                    case 2:
                    QuizCreator Quizcreator=new QuizCreator();
                    Quizcreator.Quizcreator();
                        break;
                    case 3:
                    groupdiscussion groupdiscussion=new groupdiscussion();
                    groupdiscussion.startGroupDiscussion();
                        break;
                    case 0: 
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter a valid option (1-3).\n");
                        continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine(); // Clear the invalid input from the input buffer
                continue;
            }
        }
    }

  // to create new course 
   static class CoursePortal {
        private static final String COURSES_FOLDER = "courses";
        private static final String END_COMMAND = "(end)";
        private static final String COMPLETE_LATER_COMMAND = "(complete later)";
    
        public  void Courseportal() {
            Scanner scanner = new Scanner(System.in);
    
            System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t|                  \u001B[0m\u001B[1mCourse Portal\u001B[0m\u001B[33m                    |");
            System.out.println("\t\t\t\t\t\t+---------------------------------------------------+\u001B[0m");
            System.out.println("\t\t\t\t\t\t\033[0m\033[33m|               Please select an option             |");
            
            int choice = -1;
            while (choice != 0) {
                System.out.println("\t\t\t\t\t\t|            \033[32m Press 1: Create a New Course\033[33m          | ");
                System.out.println("\t\t\t\t\t\t|            \033[32m Press 2: Edit a Previous Course\033[33m       |");
                System.out.println("\t\t\t\t\t\t|            \033[32m Press 0: Exit\033[33m                         |");
                System.out.println("\t\t\t\t\t\t+---------------------------------------------------+\u001B[0m");
                System.out.print("\033[33m\nEnter your choice: \033[0m");
    
                while (!scanner.hasNextInt()) {
                    System.out.print("\033[31\t\t\t\t    mInvalid input. Please enter a valid choice: \033[0m");
                    scanner.next();
                }
    
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
    
                switch (choice) {
                    case 1:
                        createNewCourse(scanner);
                        break;
                    case 2:
                        editPreviousCourse(scanner);
                        break;
                    case 0:
                        System.out.println("\n\t\t\t\t\t\t\t\t\033[31mReturning to previous page...\033[0m");
                        break;
                    default:
                        System.out.println("\t\t\t\t\t\t\t\033[31mInvalid choice. Please enter a valid option.\033[0m");
                        break;
                }
            }
    
            scanner.close();
        }
    
        private static void createNewCourse(Scanner scanner) {
            System.out.println("\n\t\t\t\t\t\t\t\t\033[32m==== Create a New Course ====\033[0m");
            System.out.print("\033[33mEnter the name of the course: \033[0m");
           
            String courseName = scanner.nextLine();
            System.out.println("\t\t\t\t\t\t\t \033[32m Type (end) to submit the course");
            System.out.println("\t\t\t\t\t\t \033[32m Type (complete later) to save the course for later \033[0m");
    
            try {
                File courseFolder = new File(COURSES_FOLDER);
                if (!courseFolder.exists()) {
                    courseFolder.mkdirs();
                }
    
                String courseFilePath = COURSES_FOLDER + "/" + courseName + ".txt";
                FileWriter fileWriter = new FileWriter(courseFilePath, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    
                int pageNumber = 1;
                String pageContent = "";
    
                while (true) {
                    System.out.print("\033[34mPage " + pageNumber + ": \033[0m");
                    String input = scanner.nextLine();
    
                    if (END_COMMAND.equals(input)) {
                        System.out.print("\t\t\t\t\t    \033[32m Do you want to complete the course and submit it?\033[33m (yes/no): \033[0m");
                        String submissionChoice = scanner.nextLine().toLowerCase();
    
                        while (!submissionChoice.equals("yes") && !submissionChoice.equals("no")) {
                            System.out.print("\t\t\t\t\t\t\t\033[31mInvalid input. Please enter 'yes' or 'no': \033[0m");
                            submissionChoice = scanner.nextLine().toLowerCase();
                        }
    
                        if (submissionChoice.equals("yes")) {
                            bufferedWriter.write("[page]: " + pageNumber + "\n");
                            bufferedWriter.write(pageContent);
                            bufferedWriter.newLine();
                            bufferedWriter.close();
    
                            System.out.println("\t\t\t\t\t\t\t\033[32m Course saved successfully.\033[0m");
                            break;
                        } else {
                            System.out.println("\t\t\t\t\t\t\t\033[31mCourse not submitted. You can continue writing.\033[0m");
                        }
                    } else if (COMPLETE_LATER_COMMAND.equals(input)) {
                        bufferedWriter.write("[page]: " + pageNumber + "\n");
                        bufferedWriter.write(pageContent);
                        bufferedWriter.newLine();
                        bufferedWriter.close();
    
                        System.out.println("\t\t\t\t\t\t\t\t\033[32mCourse saved for completion later.\033[0m");
                        break;
                    } else {
                        pageContent += input + "\n";
    
                        if (pageContent.split("\\s+").length > 20) {
                            bufferedWriter.write("[page]: " + pageNumber + "\n");
                            bufferedWriter.write(pageContent);
                            bufferedWriter.newLine();
                            pageContent = "";
                            pageNumber++;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\t\t\t\033[31mAn error occurred while creating the course: \033[0m" + e.getMessage());
            }
        }
    
        private static void editPreviousCourse(Scanner scanner) {
            System.out.println("\t\t\t\t\t\t\t\t\n\033[33m ==== Edit a Previous Course ====");
            File coursesFolder = new File(COURSES_FOLDER);
            File[] courseFiles = coursesFolder.listFiles();
        
            if (courseFiles != null && courseFiles.length > 0) {
                for (int i = 0; i < courseFiles.length; i++) {
                    System.out.println((i + 1) + ". " + courseFiles[i].getName().replace(".txt", ""));
                }
        
                int courseNumber = -1;
                while (courseNumber < 1 || courseNumber > courseFiles.length) {
                    System.out.print("\033[33m Enter the number of the course you want to edit (0 to cancel): \033[0m");
        
                    while (!scanner.hasNextInt()) {
                        System.out.print("\t\t\t\t\t\t\t\t\033[31mInvalid input. Please enter a valid course number:\033[0m ");
                        scanner.next();
                    }
        
                    courseNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
        
                    if (courseNumber == 0) {
                        return;
                    }
                }
        
                try {
                    String courseFilePath = courseFiles[courseNumber - 1].getAbsolutePath();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(courseFilePath));
                    String line;
                    boolean readingContent = false;
                    int pageNumber = 1;
        
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.matches("\\[page\\]: \\d+")) {
                            readingContent = true;
                            System.out.println(line);
                            pageNumber = Integer.parseInt(line.split(": ")[1]);
                        } else if (readingContent) {
                            System.out.println(line);
                        }
                    }
        
                    bufferedReader.close();
        
                    FileWriter fileWriter = new FileWriter(courseFilePath, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String pageContent = "";
        
                    while (true) {
                        System.out.print("\033[34m[page]: " + pageNumber + "\nPage " + pageNumber + ": \033[0m");
                        String input = scanner.nextLine();
        
                        if (END_COMMAND.equals(input)) {
                            System.out.print("\t\t\t\t\t\033[33m Do you want to complete the course and submit it? \033[32m(yes/no): \033[0m");
                            String submissionChoice = scanner.nextLine().toLowerCase();
        
                            while (!submissionChoice.equals("yes") && !submissionChoice.equals("no")) {
                                System.out.print("\t\t\t\t\t\t\t \033[31 mInvalid input. Please enter 'yes' or 'no': \033[0m");
                                submissionChoice = scanner.nextLine().toLowerCase();
                            }
        
                            if (submissionChoice.equals("yes")) {
                                bufferedWriter.write("[page]: " + pageNumber + "\n");
                                bufferedWriter.write(pageContent);
                                bufferedWriter.newLine();
                                bufferedWriter.close();
        
                                System.out.println("\t\t\t\t\t\t\t\033[32 mCourse saved successfully.\033[0m");
                                break;
                            } else {
                                System.out.println("\t\t\t\t\t\033[31mCourse not submitted. You can continue writing.\033[0m");
                            }
                        } else if (COMPLETE_LATER_COMMAND.equals(input)) {
                            bufferedWriter.write("[page]: " + pageNumber + "\n");
                            bufferedWriter.write(pageContent);
                            bufferedWriter.newLine();
                            bufferedWriter.close();
        
                            System.out.println("\t\t\t\t\t\t\t\033[32mCourse saved for completion later.\033[0m");
                            break;
                        } else {
                            pageContent += input + "\n";
        
                            if (pageContent.split("\\s+").length > 20) {
                                bufferedWriter.write("[page]: " + pageNumber + "\n");
                                bufferedWriter.write(pageContent);
                                bufferedWriter.newLine();
                                pageContent = "";
                                pageNumber++;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("\t\t\t\t\t\t\t\033[31mAn error occurred while editing the course: " + e.getMessage()+"\033[0m");
                }
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\033[31m No previous courses found.\033[0m");
            }
        }
    }    
}

// code for quiz creator 
class QuizCreator {
    public void Quizcreator() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();
        String quizName = "";

        System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");
        System.out.println("\t\t\t\t\t\t|                  \u001B[0m\u001B[1mCreate New Quiz\u001B[0m\u001B[33m                  |");
        System.out.println("\t\t\t\t\t\t+---------------------------------------------------+\u001B[0m");

        // Get quiz name
        System.out.print("\t\t\t\t\t\t    \033[33m Enter quiz name: \033[0m");
        quizName = scanner.nextLine();

        try {
            FileWriter fileWriter = new FileWriter("quiz/" + quizName + ".txt");

            // Get questions and options
            String userInput = "";
            int questionCounter = 1;

            while (!userInput.equals("finish")) {
                System.out.print("\u001B[36mEnter question " + questionCounter + ": \u001B[0m");
                userInput = scanner.nextLine();

                if (userInput.equals("previous")) {
                    if (questions.size() > 0) {
                        questions.remove(questions.size() - 1);
                        questionCounter--;
                    } else {
                        System.out.println("\t\t\t\t\t\t\033[31m No previous question found. Please enter a new question.\033[0m ");
                    }
                } else if (!userInput.equals("finish")) {
                    Question question = new Question();
                    question.setQuestion(userInput);

                    int numOptions = 0;
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("\u001B[36m Enter the number of options: \u001B[0m");
                            numOptions = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("\t\t\t\t\t\t\033[31mInvalid input. Please enter a number.\033[0m ");
                            scanner.nextLine(); // Consume invalid input
                        }
                    }

                    for (int i = 0; i < numOptions; i++) {
                        System.out.print("\u001B[36m Enter option " + (char)('A' + i) + ": \u001B[0m");
                        question.addOption(scanner.nextLine());
                    }

                    String correctOption = "";
                    while (!question.hasCorrectOption()) {
                        System.out.print("\033[36mEnter the correct option: \033[0m");
                        correctOption = scanner.nextLine().toUpperCase();

                        if (correctOption.length() == 1 && correctOption.charAt(0) >= 'A' && correctOption.charAt(0) < 'A' + numOptions) {
                            question.setCorrectOption(correctOption);
                        } else {
                            System.out.println("\t\t\t\t\t\t\033[31mInvalid input. Please enter a valid option (A, B, C, etc.).\033[0m");
                        }
                    }

                    questions.add(question);
                    questionCounter++;
                }
            }

            // Write questions and answers to file
            for (Question question : questions) {
                fileWriter.write("Question: " + question.getQuestion() + "\n");

                List<String> options = question.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    fileWriter.write("Option " + (char)('A' + i) + ": " + options.get(i) + "\n");
                }

                fileWriter.write("Correct Option: " + question.getCorrectOption() + "\n\n");
            }

            fileWriter.close();
            System.out.println("\t\t\t\t\t\t\t\033[33m Quiz created successfully.\033[0m");

        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\t\033[31mAn error occurred while creating the quiz.\033[0m");
            e.printStackTrace();
        }
    }
}

class Question {
    private String question;
    private List<String> options;
    private String correctOption;

    public Question() {
        options = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void addOption(String option) {
        options.add(option);
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public boolean hasCorrectOption() {
        return correctOption != null && !correctOption.isEmpty();
    }
}
// code for group discussion

 class groupdiscussion {
    private static final String END_COMMAND = "(end)";
    private static final String MESSAGES_FILE = "messages.txt";
    private static final String ADMIN_USERNAME = "admin";

    public void startGroupDiscussion() {
        String username = ADMIN_USERNAME;

        System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");
        System.out.println("\t\t\t\t\t\t|         \u001B[0m\u001B[1m    Group Discussion Platform\u001B[0m\u001B[33m             |");
        System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");

        System.out.println("\t\t\t\t\t     \033[0m\033[32mType your message or '@username message' to tag a specific user.");
        System.out.println("\t\t\t\t\t\t\t    \033[0m\033[32mEnter '(end)' to exit the program. \033[0m");

        try {
            List<String> messages = getPastMessages();
            for (String message : messages) {
                System.out.println(formatMessage(message));
            }

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (END_COMMAND.equals(input)) {
                    System.out.println(" \033[0m\033[31mExiting the program... \033[0m");
                    break;
                }

                try {
                    if (input.startsWith("@")) {
                        // Tagged message, extract the tagged usernames
                        List<String> taggedUsernames = extractTaggedUsernames(input);
                        if (!taggedUsernames.isEmpty()) {
                            sendMessageToUsers(username, taggedUsernames, input);
                        } else {
                            System.out.println("Invalid format for tagged message. Try again.");
                        }
                    } else {
                        // Broadcast message to all users
                        String broadcastMessage = formatAdminMessage(username, input);
                        appendMessageToFile(MESSAGES_FILE, broadcastMessage);
                    }
                } catch (IOException e) {
                    System.out.println("\t\t\t\t\t\t\t\033[31m[ An error occurred: \033[0m[" + e.getMessage());
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Exiting the program...");
        }
    }

    private static void sendMessageToUsers(String senderUsername, List<String> recipientUsernames, String message)
            throws IOException {
        for (String recipientUsername : recipientUsernames) {
            String taggedMessage = formatAdminMessage(senderUsername, message);
            appendMessageToFile(MESSAGES_FILE, taggedMessage);

            if (recipientUsername.equals(senderUsername)) {
                System.out.println(taggedMessage);
            }
        }
    }

    private static void appendMessageToFile(String filePath, String message) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
        }
    }

    private static List<String> extractTaggedUsernames(String input) {
        List<String> taggedUsernames = new ArrayList<>();
        String[] parts = input.split("\\s+");
        for (String part : parts) {
            if (part.startsWith("@")) {
                taggedUsernames.add(part.substring(1));
            }
        }
        return taggedUsernames;
    }

    private static List<String> getPastMessages() throws FileNotFoundException {
        List<String> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MESSAGES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\t\t \033[31m[ An error occurred while reading past messages:\033[0m[ " + e.getMessage());
        }
        return messages;
    }

    private static String formatMessage(String message) {
        if (message.startsWith("[" + ADMIN_USERNAME + "]")) {
            return "\033[35m" + message + "\033[0m";
        } else {
            return message;
        }
    }

    private static String formatAdminMessage(String username, String message) {
        return "\033[35m[" + ADMIN_USERNAME + "] \033[0m" + message;
    }
}
