import java.util.*;
import java.io.*;

public class mainPage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int choice = 0;
        

        while (!validInput) {
            try {
                System.out.println("\033[33m\t\t\t\t\t\t+---------------------------------------------------+");
                System.out.println("\t\t\t\t\t\t|                  \033[0m\033[1mEducation System\033[0m\033[33m                 |");
                System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
                System.out.println("\t\t\t\t\t\t|              Please select an option              |");
                System.out.println("\t\t\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t\t\t|                \033[32mPress 1: Login\033[0m\033[33m                     |");
                System.out.println("\t\t\t\t\t\t|                \033[32mPress 2: New Signup\033[0m\033[33m                |");
                System.out.println("\t\t\t\t\t\t|                \033[32mPress 3: About\033[0m\033[33m                     |");
                System.out.println("\t\t\t\t\t\t|                                                   |");
                System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
                System.out.print("\033[33mEnter your choice:\033[0m ");
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 3) {
                    validInput = true;
                } else {
                    System.out.println("\u001B[31mInvalid input. Please enter a valid choice.\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println("\u001B[31mInvalid input. Please enter a valid choice.\u001B[0m");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
            Login login = new Login();
            login.main(args);
                break;
            case 2:
                StudentSignup studentSignup = new StudentSignup();
                studentSignup.StudentSignup();

                break;
            case 3:
                System.out.println("You selected About.");
                System.out.println("\t\t\t\t\t\t\u001B[31m Aaryan Brar 22CSU219");
                System.out.println("\t\t\t\t\t\t\u001B[31m Arushi Bhat 22CSU406");
                System.out.println("\t\t\t\t\t\t\u001B[31m Nischal Sharma 22CSU211");
                System.out.println("\t\t\t\t\t\t\u001B[31m Agrima Sharma 22CSU215");
                break;
            default:
                break;
        }

        scanner.close();
    }
}

class StudentSignup {
    public void StudentSignup() {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        Scanner Signup_input = new Scanner(System.in);

        System.out.println("\033[33m\t\t\t\t\t\t+---------------------------------------------------+");
        System.out.println("\t\t\t\t\t\t|                  \033[0m\033[1mNew signup\033[0m\033[33m                       |");
        System.out.println("\t\t\t\t\t\t+---------------------------------------------------+");
        boolean usernameExists = true;
        while (usernameExists) {
            System.out.print("\n\t\t\t\t\t\t\t\033[32m  Enter Username:  \033[0m ");
            String username = Signup_input.nextLine();
            String userFile = username + ".txt";

            // creating a new file for each member to store info.
            File myFile = new File(userFile);
            if (myFile.exists()) {
                System.out.println(red + "\t\t\t\t\t\t\t The username already exists \n\t\t\t\t\t\t\t please try another username"
                        + reset);
                     
                continue; // Ask for re-entering the username
            }
             else 
            {
                try 
                {
                    myFile.createNewFile();
                    usernameExists = false; // exit loop when unique username is found
                } catch (IOException e) 
                {
                    System.out.println(red
                            + "\t\t\t\t\t\tCannot create the file at the moment. \n\t\t\t\t\t\t\t please try again later!!!"
                            + reset);
                }
            }
                try
                {
                    FileWriter fileWriter=new FileWriter(userFile);
        
                    System.out.print("\t\t\t\t\t\t\t\033[32m  First Name:    \033[0m ");
                    String firstName=Signup_input.nextLine();
                    fileWriter.write("\nfirstname: "+firstName+" ");

                     System.out.print("\t\t\t\t\t\t\033[32m          ] Last Name:      \033[0m");
                    String lastName=Signup_input.nextLine();
                    fileWriter.write("\nlastName: "+lastName+" ");
                   
                    int age = 0;
                    while (true) {
                        try {
                            System.out.print("\t\t\t\t\t\t \033[32m         Age:            \033[0m");
                            age = Integer.parseInt(Signup_input.nextLine());  // show error if age entered is not a int
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(red + "\n\t\t\t\t\t\t\t   AGE MUST BE A NUMBER\n\t\t\t\t\t\t\t   PLEASE RE-ENTER AGE\n" + reset);
                        }
                    }
                    fileWriter.write("\nage: "+age+" ");

                    int standard = 0;
                    while (true) {
                        try {
                            System.out.print("\t\t\t\t\t\t \033[32m         Standard:       \033[0m");
                            standard = Integer.parseInt(Signup_input.nextLine());   // show error if standard entered is not an int
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(red + "\n\t\t\t\t\t\t\t   STANDARD MUST BE A NUMBER\n\t\t\t\t\t\t\t  PLEASE RE-ENTER STANDARD\n" + reset);
                        }
                    }
                    fileWriter.write("\nstandard: "+standard+" ");

                    int passwordStrength=0;
                    while(passwordStrength==0)
                    {
                         System.out.print("\t\t\t\t\t\t \033[32m         Enter password:\033[0m ");
                        String password1= Signup_input.nextLine();
                        if(password1.length()<8)
                         {
                            System.out.print(red+"\n\n\t\t\t\t\t\t\t PASSWORD ENTERED Is WEEK \n\t\t\t\t\t\t      gwe RE-ENTER A STRONG PASSWORD\n"+reset);
                         }
                         else
                         {
                            System.out.print("\t\t\t\t\t\t \033[32m         Confirm password: \033[0m");
                            String password2= Signup_input.nextLine();
                            if (password2.equals(password1))
                            {
                            fileWriter.write("\npassword: "+password2+" ");
                            fileWriter.close();
                            passwordStrength=1;
                            System.out.println("\t\t\t\t\t\t\t ACCOUNT CREATED SUCCESSFULLY");
                            break;
                            }
                            else
                            {
                                System.out.println(red+"\n\t\t\t\t\t\t\t  THE PASSWORDS DO NOT MATCH \n\t\t\t\t\t\t\t     RE-ENTER PASSWORD"+reset);
                            }
                         }
                    }
                 }
                    catch(IOException e)
                    {
                        System.out.println(red+"\t\t\t\t\t\t\tAN ERROR OCCURED"+reset);
                        
                    }
            }
    }
}

