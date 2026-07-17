package BankSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

// This Serves as the Controller
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

            try {
	            rootLevelChoice = scanner.nextInt();
	            scanner.nextLine(); // Cleanup \n
            }

            // Non-Integer Input
            catch(InputMismatchException e) {
            	scanner.nextLine(); // Cleanup \n
            }

            // Early exit
            if (rootLevelChoice == 3) {
            	System.out.println("--- Exiting Bank Program ---");
            	return;
            }

            // Create User
            else if (rootLevelChoice == 2){
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
            else if (rootLevelChoice == 1){
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
                	scanner.nextLine();


                	switch (userChoice) {
                	case 1:
                		// Create SavingsAccount
                		user.createSavingsAccount(scanner);
                		break;
                	case 2:
                		// Create LoanRepaymentAccount
                		user.createLoanRepaymentAccount(scanner);
                		break;
                	case 3:
                		// Perform Transaction
                		performTransaction(scanner, user);
                		break;
                	case 4:
                		// Advance One Month
                		System.out.println("Advancing Time By One Month Forward...");
                		user.advanceMonth();
                		break;
                	case 5:
                		user.accountEnquiry(scanner);
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

	            // Non-Choice Number Input
	            else {
	            	System.out.println("Please select a valid input.");
	            }
            }
        }

    // Helper method
    private void performTransaction(Scanner scanner, User user) {
    	System.out.println("Please select an account to perform your transaction: ");
		// Select User Account
		BankAccount account = user.chooseAccount(scanner);
		// Exit if no accounts are available
		if (account == null) {
			return;
		}

    	int userInput;
    	System.out.println("Please select an action: ");
    	System.out.println("1. Deposit");
    	System.out.println("2. Withdrawal");
    	try {
    		userInput = scanner.nextInt();
    		scanner.nextLine();
    	}
    	catch(InputMismatchException e) {
    		System.out.println("Error: please input a number.");
    		return;
    	}
    	if (userInput == 1) {
    		// Ask for deposit amount etc
    		// account.deposit(amount, note, timestamp);
    	}
    	else {
    		// withdraw
    	}

    }
}
