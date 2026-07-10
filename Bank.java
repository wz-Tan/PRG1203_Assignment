package BankSystem;

import java.util.Arrays;
import java.util.Scanner;

// This Serves as the Main Loop / Program
public class Bank {
    private Auth authClient = new Auth();

    // Constructor
    public Bank() {};

    // Main Loop
    void run(){
        Scanner scanner = new Scanner(System.in);
        int rootLevelChoice = 0;

        // Init Message
        System.out.println("--- Bank Program Starting ---");

        while (rootLevelChoice != 3){

            System.out.println("Users are " + authClient.getAllUsers());

            System.out.println("\nPlease Choose An Action: \n 1 - Sign In \n 2 - Create New User \n 3 - Exit");
            rootLevelChoice = scanner.nextInt();
            scanner.nextLine(); // Cleanup \n

            // Early exit
            if (rootLevelChoice == 3) return;

            // Create User
            if (rootLevelChoice == 2){
                // Collect Username and Password, Exit if Null Returned (User Chose to Quit)
                String username = authClient.inputUsername(scanner);
                if (username == null ) continue;

                String password = authClient.inputPassword(scanner);
                if (password == null ) continue;

                authClient.saveUser(username, password);
                System.out.println("Succesfully created user.");
            }

            // Sign In (Core Function)
            if (rootLevelChoice == 1){
                // Collect Username and Password, Exit if Null Returned (User Chose to Quit)
                String username = authClient.inputUsername(scanner);
                if (username == null) continue;

                User user = authClient.getUser(username);
                // No Record Found
                if (user == null ) continue;

                // TODO: Succesfully Found User
                System.out.println("Sign in Succesfully");

                }
            }


        }
    }
