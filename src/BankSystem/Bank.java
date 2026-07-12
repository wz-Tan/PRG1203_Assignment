package BankSystem;

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
            if (rootLevelChoice == 3) { 
            	System.out.println("--- Exiting Bank Program ---");
            	return;
            }

            // Create User
            if (rootLevelChoice == 2){
                // Collect Username
                String username;

                while (true) {
                    username = authClient.inputUsername(scanner);

                    // Choosing to quit returns null
                    if (username == null) break;

                    // Check Duplicate Username
                    if (authClient.ifUsernameExists(username)) {
                        System.out.println("Username already exists. Please try again.");
                        continue;
                    }

                    else{
                        break;
                    }
                }

                // Outer Layer Quit
                if (username == null) continue;

                // Collect Password
                String password = authClient.inputPassword(scanner);
                if (password == null ) continue;

                authClient.saveUser(username, password);
                System.out.println("Succesfully created user.");
            }

            // Sign In (Core Function)
            if (rootLevelChoice == 1){
                User user = null;

                while (true){
                    // Username Check
                    String username = authClient.inputUsername(scanner);
                    if (username == null) break;

                    // Collect Password
                    String password = authClient.inputPassword(scanner);
                    if (password == null ) break;

                    // Returns Null if Invalid, Object if Valid
                    user = authClient.signInUser(username, password);

                    // Exit Loop Once Received Proper User Object
                    if (user!=null) break;
                }

                // Inner Loop Exited Early from Username or Password
                if (user == null ) continue;

                System.out.println("Signed in Succesfully!");
                
                int userChoice = 0;
                
                // Second Loop -> Actions On Sign In
                while (userChoice!=6) {
                	System.out.printf("\nDear user %s, what would you like to do today? \n\n",user.getUsername());
                	System.out.println("1. Create Savings Account \n2. Create Loan Repayment Account \n3. Perform Transaction \n4. Advance One Month \n5. Account Enquiry  \n6. Sign Out");
                	
                	userChoice = scanner.nextInt();
                	
                	switch (userChoice) {
                	case 1:
                		// Create Savings Account
                		break; 
                	case 2:
                		// Create Loan Repayment Account
                		break; 
                	case 3: 
                		// Perform Transaction
                		break; 
                	case 4: 
                		// Advance One Month
                		System.out.println("Advancing Time By One Month Forward...");
                		user.advanceMonth();
                		break; 
                	case 5: 
                		// Account Enquiry
                		break; 
                	case 6:
                		// Sign Out Message
                		System.out.printf("---Signing you out, user %s---\n", user.getUsername());
                		break; 
                	default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                	}             		
                	
                }
                
                // Back to Auth Loop


                }
            }
        }
    }
