import java.io.*;
import java.util.*;

public class Student {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String age;
    private String standard;
    private File file;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
        loadStudentInfo();
    }

    private void loadStudentInfo() {
        file = new File(username + ".txt");
        if (!file.exists()) {
            System.out.println("Invalid username. Please try again.");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("firstname: ")) {
                    firstName = line.substring(11);
                } else if (line.startsWith("lastName: ")) {
                    lastName = line.substring(10);
                } else if (line.startsWith("age: ")) {
                    age = line.substring(5);
                } else if (line.startsWith("standard: ")) {
                    standard = line.substring(10);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            boolean exit = false;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (!exit) {
                System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");
                System.out.println("\t\t\t\t\t\t|              \u001B[0m\u001B[1mWelcome " + firstName + lastName + "!\u001B[0m\u001B[33m             |");
                System.out.println("\t\t\t\t\t\t+---------------------------------------------------+\u001B[0m");
                System.out.println("\t\t\t\t\t\t\033[33m|               Please select an option             |");
                System.out.println("\t\t\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t\t\t|                \033[32m Press 1: Home Page\033[0m\033[33m                |");
                System.out.println("\t\t\t\t\t\t|                \033[32m Press 2: Choice Quiz\033[0m\033[33m              |");
                System.out.println("\t\t\t\t\t\t|                \033[32m Press 3: Group Discussion\033[0m\033[33m         |");
                System.out.println("\t\t\t\t\t\t|                \033[32m Press 4: Courses\033[0m\033[33m                  |");
                System.out.println("\t\t\t\t\t\t|                \033[32m Press 5: Exit \033[0m\033[33m                    |");
                System.out.println("\t\t\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
                System.out.print("\033[33mEnter your choice:\033[0m ");
                int choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1:
                        System.out.println("Name: " + firstName + " " + lastName);
                        System.out.println("Age: " + age);
                        System.out.println("Standard: " + standard);
                        System.out.println("Do you want to reset your password? (y/n)");
                        String resetPassword = br.readLine();
                        if (resetPassword.equalsIgnoreCase("y")) {
                            System.out.println("Please enter your new password: ");
                            String newPassword = br.readLine();
                            while (newPassword.length() < 9) {
                                System.out.println("Weak password. Please enter a password with at least 9 characters: ");
                                newPassword = br.readLine();
                            }
                            System.out.println("Please confirm your new password: ");
                            String confirmPassword = br.readLine();
                            while (!confirmPassword.equals(newPassword)) {
                                System.out.println("Passwords do not match. Please confirm your new password: ");
                                confirmPassword = br.readLine();
                            }
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write("firstname: " + firstName + "\n");
                            writer.write("lastName: " + lastName + "\n");
                            writer.write("age: " + age + "\n");
                            writer.write("standard: " + standard + "\n");
                            writer.write("password: " + newPassword + "\n");
                            writer.close();
                        }
                        break;
                    case 2:
                        System.out.println("Choice Quiz:");
                        QuizTaker quizTaker = new QuizTaker();
                        quizTaker.takeQuiz();
                        break;
                    case 3:
                    groupdiscussion groupDiscussion = new groupdiscussion();
                    groupDiscussion.startGroupDiscussion(username);
                    break;
                    case 4:
                    CourseComplete courseattempt=new CourseComplete();
                    courseattempt.courseAttempt(username); // Call the main method of the CourseComplete class
                   break;
                    
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// course compplete class to start new courses 
 class CourseComplete {
    private static Scanner scanner = new Scanner(System.in);
    private static String coursesDirectoryPath = "./courses/";
    private static String usersDirectoryPath = "./";

    public void courseAttempt(String username) {
       
        
        System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");
        System.out.println("\t\t\t\t\t\t|        \u001B[0m\u001B[1m   Welcome to the Course Portal!\u001B[0m\u001B[33m           |");
        System.out.println("\t\t\t\t\t\t+---------------------------------------------------+\u001B[0m");

        while (true) {
            
            System.out.println("\t\t\t\t\t\t\033[0m\033[33m|               Please select an option             |");
            System.out.println("\t\t\t\t\t\t|                                                   |");
            System.out.println("\t\t\t\t\t\t\033[0m\033[33m|            \033[32mPress 1: To start a new course\033[0m\033[33m         |");
            System.out.println("\t\t\t\t\t\t\033[0m\033[33m|       \033[32mPress 2: To continue a previous course\033[0m\033[33m      |");
            System.out.println("\t\t\t\t\t\t\033[0m\033[33m|                  \033[32mPress 0: To exit\033[0m\033[33m                 |");
            System.out.println("\t\t\t\t\t\t\033[0m\033[33m+---------------------------------------------------+");
            System.out.print("Enter your choice: \033[0m");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    startNewCourse(username);
                } else if (choice == 2) {
                    continuePreviousCourse(username);
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("\t\t\t\t\t\t\t \033[31m Invalid choice. Please try again.\n \033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\t\t\t\t\t\t \033[31m Invalid choice. Please try again.\033[0m");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static String getUsername() {
        while (true) {
            System.out.println("\033[32m Please enter your username:");
            String username = scanner.nextLine();
            File userFile = new File(usersDirectoryPath + username + ".txt");
            if (userFile.exists()) {
                return username;
            } else {
                System.out.println("\t\t\t\t\t\t\t\033[31mUsername not found. Please try again.\033[0m");
            }
        }
    }

    private static void startNewCourse(String username) {
        List<String> courses = getCourses();
        System.out.println("\t\t\t\t\t\t\033[0m\033[33m+---------------------------------------------------+");
        for (int i = 0; i < courses.size(); i++) {

            System.out.println("\t\t\t\t\t\t\t\t\033[33m Press " + (i + 1) +": "+ courses.get(i)                     );
        }
        System.out.println("\t\t\t\t\t\t\033[0m\033[33m+---------------------------------------------------+");
        System.out.print("Enter your choice: \033[0m");
        int courseIndex;
        while (true) {
            try {
                courseIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (courseIndex >= 0 && courseIndex < courses.size()) {
                    break;
                } else {
                    System.out.println("\t\t\t\t\t\t\t\033[31m Invalid choice. Please try again.\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\t\t\t\t\t\t \033[31m Invalid choice. Please try again.\033[0m");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        String courseName = courses.get(courseIndex);
        System.out.print("\t\t\t\t\t\t\033[32m Do you really want to start the course " + courseName + "? (yes/no):\033[0m");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            int totalPages = getTotalPages(courseName);
            int currentPage = 1;
            while (true) {
                printPage(courseName, currentPage);
                System.out.println("\t\t\t\t\t\t\t \033[32m Press 1: Go to the next page -->");
                System.out.println("\t\t\t\t\t\t\t  Press 2: Go to the previous page <--");
                System.out.println("\t\t\t\t\t\t\t  Press 0: exit");
                System.out.print("\033[33m Enter your choice:\033[0m");
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == 1) {
                        currentPage++;
                        if (currentPage > totalPages) {
                            currentPage = totalPages;
                            System.out.println("\t\t\t\t\t\t\t\033[31m  You are already on the last page.\033[0m ");
                        }
                    } else if (choice == 2) {
                        currentPage--;
                        if (currentPage < 1) {
                            currentPage = 1;
                            System.out.println("\t\t\t\t\t\t\t \033[31m You are already on the 1st page.\033[0m ");
                        }
                    } else if (choice == 0) {
                        saveProgress(username, courseName, currentPage, totalPages);
                        break;
                    } else {
                        System.out.println("\t\t\t\t\t\t\t \033[31m Invalid choice. Please try again.\033[0m ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\t\t\t\t\t\t\t\033[31m  Invalid choice. Please try again.\033[0m ");
                    scanner.nextLine(); // Clear the input buffer
                }
            }
        }
    }

    private static void continuePreviousCourse(String username) {
        Map<String, Integer> userCourses = getUserCourses(username);
        List<String> courseNames = new ArrayList<>(userCourses.keySet());
        for (int i = 0; i < courseNames.size(); i++) {
            String courseName = courseNames.get(i);
            int progress = userCourses.get(courseName);
            int totalPages = getTotalPages(courseName);
            int percentageCompleted = progress * 100 / totalPages;
            if(percentageCompleted<=34){
            System.out.println("\033[32m Press " + (i + 1) + " to continue course: " + courseName + "\033[31m  []" + percentageCompleted + "% completed\033[0m ");
            }
            else if(percentageCompleted<=67){
                System.out.println("\033[0m\033[32m Press " + (i + 1) + " to continue course: " + courseName + "\033[0m\033[33m  [][]" + percentageCompleted + "% completed\033[0m "); 
            }
            else{
                System.out.println("\033[0m\033[32m Press " + (i + 1) + " to continue course: " + courseName + "\033[32m  [][][]" + percentageCompleted + "% completed\033[0m ");
            }
        }
        System.out.println("\033[32m Press 0 to return to the previous page\033[0m ");
        int courseIndex;
        try {
            courseIndex = scanner.nextInt() - 1;
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\t\t\t\t\t\t\t\033[31m Invalid choice. Please try again.\033[0m ");
            scanner.nextLine();
            return;
        }
        if (courseIndex == -1) {
            return;
        }
        if (courseIndex < -1 || courseIndex >= courseNames.size()) {
            System.out.println("\t\t\t\t\t\t\t\033[31m Invalid choice. Please try again.\033[0m ");
            return;
        }
        String courseName = courseNames.get(courseIndex);
        int totalPages = getTotalPages(courseName);
        int currentPage = userCourses.get(courseName) + 1;
        while (true) {
            printPage(courseName, currentPage);
            System.out.println("\t\t\t\t\t\t\t\033[32m Press 1: Go to the next page");
            System.out.println("\t\t\t\t\t\t\tPress 2: Go to the previous page");
            System.out.println("\t\t\t\t\t\t\tPress 0: Exit\033[0m ");
            System.out.println("\033[33m Enter your choice: \033[0m");
             int choice;
             try{
                 choice=scanner.nextInt();
                 scanner.nextLine();
    
             }catch(InputMismatchException e){
                 System.out.println("\t\t\t\t\t\t\t\033[31m Invalid choice. Please try again.\033[0m ");
                 scanner.nextLine();
                 continue;
    
             }
    
             if(choice==1){
                 if(currentPage==totalPages){
                     System.out.println("\t\t\t\t\t\t\t\033[31m You are already on the last page.\033[0m ");
    
                 }else{
                     currentPage++;
    
                 }
    
             }else if(choice==2){
                 if(currentPage==1){
                     System.out.println("\t\t\t\t\t\t\t\033[31m You are already on the first page.\033[0m ");
    
                 }else{
                     currentPage--;
    
                 }
    
             }else if(choice==0){
                 saveProgress(username,courseName,currentPage,totalPages);
                 break;
    
             }else{
                 System.out.println("\t\t\t\t\t\t\t\033[31m Invalid choice. Please try again.\033[0m ");
    
             }
    
         }
    
     }
    private static List<String> getCourses() { // returns list of all available courses
        List<String> courses = new ArrayList<>();
        File coursesDirectory = new File(coursesDirectoryPath);
        File[] courseFiles = coursesDirectory.listFiles();
        for (File courseFile : courseFiles) {
            String fileName = courseFile.getName();
            String courseName = fileName.substring(0, fileName.length() - ".txt".length());
            courses.add(courseName);
        }
        return courses;
    }

    private static Map<String, Integer> getUserCourses(String username) { // returns map of course name to last completed page number
        Map<String, Integer> userCourses = new HashMap<>();
        File userFile = new File(usersDirectoryPath + username + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("courses_")) { // assuming each line starts with courses_[coursename]=x/y
                    String[] parts = line.split("=")[0].split("_");
                    String courseName = parts[parts.length - 1];
                    int lastCompletedPageNumber = Integer.parseInt(line.split("=")[1].split("/")[0]);
                    userCourses.put(courseName, lastCompletedPageNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCourses;
    }

    private static void saveProgress(String username, String courseName, int currentPage, int totalPages) { // saves progress in format courses_[coursename]=x/y
        File userFile = new File(usersDirectoryPath + username + ".txt");
        List<String> lines = new ArrayList<>();
        boolean courseFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("courses_" + courseName + "=")) {
                    lines.add("courses_" + courseName + "=" + currentPage + "/" + totalPages);
                    courseFound = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!courseFound) {
            lines.add("courses_" + courseName + "=" + currentPage + "/" + totalPages);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printPage(String courseName, int pageNumber) { // prints content between [page]x: and [page]y:
        boolean printLineFlag = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(coursesDirectoryPath + courseName + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[page]: " + pageNumber)) {
                    printLineFlag = true;
                } else if (line.startsWith("[page]:") && printLineFlag == true) {
                    break;
                } else {
                    if (printLineFlag == true) {
                        System.out.println(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getTotalPages(String courseName) { // returns total number of pages for a given course
        int totalPages = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(coursesDirectoryPath + courseName + ".txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[page]:")) {
                    totalPages++;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();

        }

        return totalPages;

    }

}
// group discussion class for group discussion 
 class groupdiscussion {
    private static final String END_COMMAND = "(end)";
    private static final String MESSAGES_FILE = "messages.txt";
    private static final String ADMIN_USERNAME = "admin";

    public void startGroupDiscussion(String username) {
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
                        String broadcastMessage;
                        if (username.equals(ADMIN_USERNAME)) {
                            broadcastMessage = formatAdminMessage(username, input);
                        } else {
                            broadcastMessage = "[" + username + "]: " + input;
                        }
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
            String taggedMessage = "[" + senderUsername + "]: " + message;
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
        return "\033[35m[" + ADMIN_USERNAME + "] \033[0m"+ message;
    }
}

// Quiz taker class to attempt quiz 
 class QuizTaker {
    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        List<String> quizzes = getAvailableQuizzes();

        System.out.println("\u001B[33m\t\t\t\t\t\t+---------------------------------------------------+");
        System.out.println("\t\t\t\t\t\t|                    \u001B[0m\u001B[1mAttemt Quiz\u001B[0m\u001B[33m                    |");
        System.out.println("\t\t\t\t\t\t+---------------------------------------------------+\u001B[0m");

        // Display available quizzes
        System.out.println("\t\t\t\t\t\t             \u001B[33m    Available Quizzes\u001B[0m");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println("\t\t\t\t\t\t                " + (i + 1) + ". " + quizzes.get(i) + "       ");

        }

        // Get user's choice of quiz
        int choice = 0;
        while (choice < 1 || choice > quizzes.size()) {
            System.out.print("\u001B[33m Enter the quiz you want to attempt:\u001B[0m  ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (choice < 1 || choice > quizzes.size()) {
                    System.out.println("\t\t\t\t\t\t    \u001B[31m Invalid choice. Please enter a valid number.\u001B[0m ");
                }
            } catch (Exception e) {
                System.out.println("\t\t\t\t\t\t    \u001B[31m Invalid input. Please enter a number.\u001B[0m ");
                scanner.nextLine(); // Consume invalid input
            }
        }

        String quizName = quizzes.get(choice - 1);


        // Load and present the selected quiz
        String filePath = "quiz/" + quizName;
        try {
            File quizFile = new File(filePath);
            Scanner fileScanner = new Scanner(quizFile);

            List<Question> questions = new ArrayList<>();
            int score = 0;
            int totalQuestions = 0;

            // Read questions from the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith("Question: ")) {
                    Question question = new Question();
                    question.setQuestion(line.substring("Question: ".length()));
                    questions.add(question);
                    totalQuestions++;
                } else if (line.startsWith("Option ")) {
                    int optionIndex = line.charAt("Option ".length()) - 'A';
                    String option = line.substring(line.indexOf(": ") + 2);
                    questions.get(questions.size() - 1).addOption(option);
                } else if (line.startsWith("Correct Option: ")) {
                    String correctOption = line.substring("Correct Option: ".length());
                    questions.get(questions.size() - 1).setCorrectOption(correctOption);
                }
            }

            fileScanner.close();

            // Display and process questions
            System.out.println("\n\t\t\t\t\t\t\t\t\u001B[32m--- Quiz: " + quizName + " ---\u001B[0m");
            System.out.println("\t\t\t\t\t\t  \u001B[31m      NOTE: You won't be able to reset your choice ");
            for (Question question : questions) {
                System.out.println("\n\u001B[36m" + question.getQuestion() + "\u001B[0m");
                List<String> options = question.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    System.out.println("  " + (char) ('A' + i) + ". " + options.get(i));
                }

                String userAnswer = "";
                while (!isValidOption(userAnswer, options)) {
                    System.out.print(" Enter your answer (A, B, C, etc.): ");
                    userAnswer = scanner.nextLine().toUpperCase();
                    if (!isValidOption(userAnswer, options)) {
                        System.out.println("\t\t\t\t\t\t    \u001B[31m Invalid answer. Please enter a valid option.\u001B[0m");
                    }
                }

                if (userAnswer.equals(question.getCorrectOption())) {
                    score++;
                }
            }

            // Display score
            System.out.println("\n\u001B[32m\t\t\t\t\t\t\t\t--- Quiz Result ---");
            

            // Display battery-like sign
           
            if ((double)score/totalQuestions <= 0.34) {
                System.out.println("\t\t\t\t\t\t\t\t     \u001B[31m[]\u001B[0m"+ score*100/totalQuestions+"%");
            } else if ((double)score/totalQuestions <= 0.67) {
                System.out.println("\t\t\t\t\t\t\t\t     \u001B[33m[][]\u001B[0m"+ score*100/totalQuestions+"%");
            } else {
                System.out.println("\t\t\t\t\t\t\t\t      \u001B[32m[][][]\u001B[0m"+ score*100/totalQuestions+"%");
            }

        } catch (FileNotFoundException e) {
            System.out.println("\t\t\t\t\t\t    \u001B[31m Error: Quiz file not found.\u001B[0m");
        }
    }

    private static List<String> getAvailableQuizzes() {
        List<String> quizzes = new ArrayList<>();
        File folder = new File("quiz");

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String quizName = file.getName();
                        quizzes.add(quizName);
                    }
                }
            }
        }

        return quizzes;
    }

    private static boolean isValidOption(String option, List<String> options) {
        if (option.length() == 1 && option.charAt(0) >= 'A' && option.charAt(0) < 'A' + options.size()) {
            return true;
        }
        return false;
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
}
