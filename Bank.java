package BankSystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// This Serves as the Main Loop / Program
public class Bank {
    // Map userName to userObject
    private HashMap<String,User> userRecords = new HashMap<String,User>();

    // Constructor
    public Bank() {};

    // Main Loop
    void run(){
        Scanner scanner = new Scanner(System.in);
        int rootLevelChoice = 0;
        String currUsername;
        String currPassword;
        boolean usernameExists;
        User currUser;

        // Init Message
        System.out.println("--- Bank Program Starting ---");

        while (rootLevelChoice != 3){
            // Init / Reset Variables
             currUsername = "";
             currPassword = "";
             usernameExists = true;
             currUser = null;

            System.out.println("Users are "+ Arrays.asList(userRecords));

            System.out.println("\nPlease Choose An Action: \n 1 - Sign In \n 2 - Create New User \n 3 - Exit");
            rootLevelChoice = scanner.nextInt();
            scanner.nextLine(); // Cleanup \n

            // Early exit
            if (rootLevelChoice == 3) return;

            // Create User
            if (rootLevelChoice == 2){
                while (usernameExists){
                     System.out.println("Please insert your username. Or, press q to exit. ");
                     currUsername = scanner.nextLine().strip();

                     if (currUsername.equals("q") || currUsername.equals("Q")) break;

                     // Unique Username Check
                     usernameExists = ifUsernameExists(currUsername);

                     if (usernameExists){
                         System.out.println("Username already exists. Please try again. ");
                     }

                     // Non-Empty Password Check
                     else{
                         while (currPassword==""){
                             System.out.println("Please insert your password. Or, press q to exit. ");
                             currPassword = scanner.nextLine().strip();

                             if (currPassword.equals("q") || currPassword.equals("Q")) break;

                             if (currPassword==""){
                                 System.out.println("Password cannot be empty! ");
                             }
                         }
                     }
                }

                // Create User upon Valid Username and Password TODO: This gets run if I break early
                userRecords.put(currUsername, createUser(currUsername, currPassword));
                System.out.println("Succesfully created user.");
            }

            // Sign In (Core Function)
            if (rootLevelChoice == 1){
                System.out.println("Sign in");
                usernameExists = false;

                while (!usernameExists){
                     System.out.println("Please insert your username. Or, press q to exit. ");
                     currUsername = scanner.nextLine().strip();

                     if (currUsername.equals("q") || currUsername.equals("Q")) break;

                     // Unique Username Check
                     usernameExists = ifUsernameExists(currUsername);

                     if (!usernameExists){
                         System.out.println("Username does not exist. Please try again. ");
                     }

                     // Account Found
                     else{
                         currUser = userRecords.get(currUsername);

                         // Input Password
                         while (currPassword==""){
                             System.out.println("Please insert your password. Or, press q to exit. ");
                             currPassword = scanner.nextLine().strip();

                             // Early Exit
                             if (currPassword.equals("q") || currPassword.equals("Q")) break;

                             // Empty Password
                             else if (currPassword==""){
                                 System.out.println("Password cannot be empty! ");
                             }

                             // Incorrect Password
                             else if (!currUser.verifyPassword(currPassword)){
                                 System.out.println("Incorrect Password. Please Try Again.");
                             }

                             // Correct Password
                             else{
                                 System.out.println("You have succesfully signed in.");
                             }
                         }
                     }
                }
            }


        }
    }

    // User Account Management

    private boolean ifUsernameExists(String username){
        return userRecords.containsKey(username);
    }

    private User createUser(String username, String password){
        User newUser = new User(username, password);
        return newUser;
    }


}
