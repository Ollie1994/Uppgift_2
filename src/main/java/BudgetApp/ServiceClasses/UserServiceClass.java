// https://stackoverflow.com/questions/60592071/how-to-use-dates-as-keys-in-a-hashmap
// https://www.geeksforgeeks.org/java-time-localdatetime-class-in-java/
// https://www.w3schools.com/java/java_howto_loop_through_hashmap.asp
// https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java

package BudgetApp.ServiceClasses;

import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.TemplateClasses.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class UserServiceClass {

    UserInputClass userInputClass = new UserInputClass();
    HashMap<String, User> users = new HashMap<String, User>();
    HashMap<String, User> usersJson = new HashMap<String, User>(); // bara för enkelhetens skull för att hämta en tom fil sätter hashmappen till mnul och gör den svå att använda
    Gson gson = new GsonBuilder().setPrettyPrinting().create();




    //-----------------------------------------------METHODS--------------------------------------------


    public void createUserSpecificFileForExpenses(String username, String password, LocalDateTime date) throws IOException {
        String pathId = username + password;
        LocalDateTime ldt = date;
        String str = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));
        System.out.println(str);
        String pathDate = username + password + str;
        File test = new File("src/main/userSpecificFiles/" + pathId + "/" + pathDate);
        test.mkdirs();
        String path = "src/main/userSpecificFiles/" + pathId + "/" + pathDate + "/" + pathDate + "Expenses" + ".json";
        System.out.println("Path = " + path);
        FileWriter fw = new FileWriter(path);
        fw.close();
    }

    public void createUserSpecificFileForIncomes(String username, String password, LocalDateTime date) throws IOException {
        String pathId = username + password;
        LocalDateTime ldt = date;
        String str = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));
        System.out.println(str);
        String pathDate = username + password + str;
        File test = new File("src/main/userSpecificFiles/" + pathId + "/" + pathDate);
        test.mkdirs();
        String path = "src/main/userSpecificFiles/" + pathId + "/" + pathDate + "/" + pathDate + "Incomes" + ".json";
        System.out.println("Path = " + path);
        FileWriter fw = new FileWriter(path);
        fw.close();
    }


    public void createAccount() throws IOException {
        System.out.println("Please enter a username: ");
        String userName = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Please enter a password: ");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtmn = ldt.minusNanos(100);
        String str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        createUserSpecificFileForExpenses(userName, password, ldt);
        createUserSpecificFileForIncomes(userName, password, ldt);

        try {
            FileReader fr = new FileReader("src/main/users.json");
            usersJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, User>>() {
            }.getType());
            if (usersJson == null) {
                System.out.println("usersJson = null"); // test ta bort sen
            } else {
                System.out.println("usersJson = not empty"); // test ta bort sen
                users = usersJson;
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("catch ?");
        }

        users.put(str, new User(userName, password));
        System.out.println("You have successfully created a new account with username " + userName + " and password " + password);
        FileWriter fw = new FileWriter("src/main/users.json");
        gson.toJson(users, fw);
        fw.close();

    }

    public boolean login() throws IOException {
        int i = 0;
        boolean loggedIn = false;
        while (i < 3 && loggedIn == false) { // 3 test logins tills ool
            try {
                i++;
                System.out.println("Please enter your username");
                String userName = userInputClass.inputUsernamePasswordDateChoice();
                System.out.println("Please enter your password");
                String password = userInputClass.inputUsernamePasswordDateChoice();
                try {
                    FileReader fr = new FileReader("src/main/users.json");
                    users = new Gson().fromJson(fr, new TypeToken<HashMap<String, User>>() {
                    }.getType());
                    fr.close();
                } catch (Exception e) {
                    System.out.println("catch ?");
                }
                for (User user : users.values()) {
                    if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                        System.out.println("You have successfully logged in!");
                        loggedIn = true;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("No users found");
            }
        }
        if (loggedIn == false) {
            System.out.println("You have not successfully logged in!");
        }
        return loggedIn;
    }

    public void deleteAccount() throws IOException {
        boolean usersFound = displayAccounts();
        if (usersFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of user you want to remove");
            String date = userInputClass.inputUsernamePasswordDateChoice();
            users.remove(date);
            FileWriter fw = new FileWriter("src/main/users.json");
            gson.toJson(users, fw);
            fw.close();
        } else {
            System.out.println("No users found");
        }
    }

    public boolean displayAccounts() throws IOException { // bara för testing
        boolean usersFound = true;
        try {
            FileReader fr = new FileReader("src/main/users.json");
            usersJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, User>>() {
            }.getType());
            if (usersJson == null) {
                System.out.println("usersJson = null"); // test ta bort sen
                usersFound = false;
            } else {
                System.out.println("usersJson = not empty"); // test ta bort sen
                users = usersJson;
            }
            for (String i : users.keySet()) {
                System.out.println("User\nKey - " + i + "\nUser - " + users.get(i));
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No users found");
            usersFound = false;
        }
        return usersFound;
    }


}
