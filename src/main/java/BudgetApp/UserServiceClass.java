package BudgetApp;

import java.util.HashMap;

public class UserServiceClass {

    TryCatch tryCatch = new TryCatch();
    HashMap<String, User> users = new HashMap<>();

    public void createAccount() {
        System.out.println("Please enter a username: ");
        String userName = tryCatch.TryCatch2();
        System.out.println("Please enter a password: ");
        String password = tryCatch.TryCatch2();
        users.put("1", new User(userName, password)); // fixa så att 1an uppdateras med +1 varjegång vi skapar en ny user -
        // genom att kolla om 1 redan finns
        System.out.println("You have successfully created a new account with username " + userName + " and password " + password);

       // displayAccounts(); //ta bort sen
    }
    public boolean login() {
        int i = 0;
        boolean loggedIn = false;
        while (i < 3 && loggedIn == false) { // 3 test logins tills ool
            i ++;
            System.out.println("please enter username");
            String userName = tryCatch.TryCatch2();
            System.out.println("please enter password");
            String password = tryCatch.TryCatch2();
            for (User user : users.values()) {
                if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    System.out.println("You have successfully logged in!");
                     loggedIn = true;
                } else {
                    System.out.println("You have unsuccessfully logged in!");
                }
            }
        }
        return loggedIn;
    }






    public void displayAccounts() {
        for (User user : users.values()) {
            System.out.println(user);
        }
    }



}
