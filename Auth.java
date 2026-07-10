package BankSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// Responsible for Signing In / Creating User
public class Auth {
    // Map userName to userObject
    private HashMap<String,User> userRecords = new HashMap<String,User>();
    private User currentUser;

    public Auth(){}

    // Ask for Username Input (On Success Return the String, On Fail Return Null)
    String inputUsername(Scanner scanner){
        String userInput;

        while (true){
             System.out.println("Please insert your username. Or, press q to exit. ");
             userInput = scanner.nextLine().strip();

             // Early Exit
             if (userInput.equals("q") || userInput.equals("Q")){
                 return null;
             }

             // Empty Input
             else if (userInput.equals("")){
                 System.out.println("Username cannot be empty!");
             }

             // Valid Username
             else{
                return userInput;
             }
        }
    }

    // Ask for Password Input (On Success Return the String, On Fail Return Null)
    String inputPassword(Scanner scanner){
        String userInput;

        while (true){
             System.out.println("Please insert your password. Or, press q to exit. ");
             userInput = scanner.nextLine().strip();

             // Early Exit
             if (userInput.equals("q") || userInput.equals("Q")){
                 return null;
             }

             // Empty Input
             else if (userInput.equals("")){
                 System.out.println("Password cannot be empty!");
             }

             // Valid Password
             else{
                return userInput;
             }
        }
    }

    // Adding User into Map
    void saveUser(String username, String password){
        User newUser = new User(username, password);
        userRecords.put(username, newUser);
    }

    // Sign In User

    // Sign Out
    private void signOutUser(){
        currentUser = null;
    }

    // Unique Username Check TODO: Apply for Creating Account
    private boolean ifUsernameExists(String username){
        return userRecords.containsKey(username);
    }

    // Returning User Objects
    ArrayList<User> getAllUsers(){
        ArrayList<User> userObjectList = new ArrayList<User>(userRecords.values());
        return userObjectList;
    }

    User getUser(String username){
        User user = userRecords.get(username);

        if (user == null){
            System.out.println("User not found. Please try again.");
        }

        return user;
    }
}
