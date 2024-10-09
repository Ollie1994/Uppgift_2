// https://stackoverflow.com/questions/60592071/how-to-use-dates-as-keys-in-a-hashmap
// https://www.geeksforgeeks.org/java-time-localdatetime-class-in-java/
// https://www.w3schools.com/java/java_howto_loop_through_hashmap.asp


package BudgetApp.ServiceClasses;

import BudgetApp.TemplateClasses.User;
import BudgetApp.UserInputClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class UserServiceClass {

    UserInputClass userInputClass = new UserInputClass();
    HashMap<String, User> users = new HashMap<String, User>();










    //-----------------------------------------------METHODS--------------------------------------------
    public void createAccount() throws IOException {
        System.out.println("Please enter a username: ");
        String userName = userInputClass.inputUsernamePasswordChoice();
        System.out.println("Please enter a password: ");
        String password = userInputClass.inputUsernamePasswordChoice();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtmN = ldt.minusNanos(100);
        String str = ldtmN.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        users.put(str, new User(userName, password));
        System.out.println("You have successfully created a new account with username " + userName + " and password " + password);
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/main/user.json");
        gson.toJson(users, fw);
        fw.close();

    }
    public boolean login() throws IOException {
        int i = 0;
        boolean loggedIn = false;
        while (i < 3 && loggedIn == false) { // 3 test logins tills ool
            i ++;
            System.out.println("Please enter your username");
            String userName = userInputClass.inputUsernamePasswordChoice();
            System.out.println("Please enter your password");
            String password = userInputClass.inputUsernamePasswordChoice();
            Gson gson = new Gson();
            FileReader fr = new FileReader("src/main/user.json");
            users = new Gson().fromJson(fr, new TypeToken<HashMap<String, User>>(){}.getType());
            fr.close();
            for (User user : users.values()) {
                if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    System.out.println("You have successfully logged in!");
                     loggedIn = true;
                }
            }
        }

        if (loggedIn == false) {System.out.println("You have not successfully logged in!");
        }
        return loggedIn;
    }

    public void displayAccounts() throws IOException { // bara för testing
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/main/user.json");
        users = new Gson().fromJson(fr, new TypeToken<HashMap<String, User>>(){}.getType());
        for (String i : users.keySet()) {
            System.out.println("User\nKey - " + i + "\nUser - " + users.get(i));
        }
        fr.close();
    }



}
