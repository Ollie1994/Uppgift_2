// https://stackoverflow.com/questions/60592071/how-to-use-dates-as-keys-in-a-hashmap
// https://www.geeksforgeeks.org/java-time-localdatetime-class-in-java/
// https://www.w3schools.com/java/java_howto_loop_through_hashmap.asp


package BudgetApp;

import java.time.LocalDateTime;
import java.util.HashMap;

public class UserServiceClass {

    TryCatch tryCatch = new TryCatch();
    HashMap<LocalDateTime, User> users = new HashMap<LocalDateTime, User>();










    //-----------------------------------------------METHODS--------------------------------------------
    public void createAccount() {
        System.out.println("Please enter a username: ");
        String userName = tryCatch.TryCatch2();
        System.out.println("Please enter a password: ");
        String password = tryCatch.TryCatch2();
        users.put(LocalDateTime.now(), new User(userName, password)); // fixa så att 1an uppdateras med +1 varjegång vi skapar en ny user -
        System.out.println("You have successfully created a new account with username " + userName + " and password " + password);
    }


    public boolean login() {
        int i = 0;
        boolean loggedIn = false;
        while (i < 3 && loggedIn == false) { // 3 test logins tills ool
            i ++;
            System.out.println("Please enter your username");
            String userName = tryCatch.TryCatch2();
            System.out.println("Please enter your password");
            String password = tryCatch.TryCatch2();
            for (User user : users.values()) {
                if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    System.out.println("You have successfully logged in!");
                     loggedIn = true;
                }
            }
        }
        if (loggedIn == false) {System.out.println("You have not successfully logged in!");}
        return loggedIn;
    }

    public void displayAccounts() {
        for (LocalDateTime i : users.keySet()) {
            System.out.println("User\nKey - " + i + "\nUser - " + users.get(i));
        }
    }



}
