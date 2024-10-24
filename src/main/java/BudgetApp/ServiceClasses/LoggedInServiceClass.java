package BudgetApp.ServiceClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoggedInServiceClass {


    /*
    Denna klassen existerar bara för att annars blir det:
    ExpenseStorageServiceClass expenseStorageServiceClass = new ExpenseStorageServiceClass();
    kallar på
    UserServiceClass userServiceClass = new UserServiceClass();
    vilket kallar på
    ExpenseStorageServiceClass expenseStorageServiceClass = new ExpenseStorageServiceClass();
    vilket kallar på
    UserServiceClass userServiceClass = new UserServiceClass();
    i en oändlig looooOOOOOooop, tills det kastar ett fel(1000fel).
     */



    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String userLoggedInJson;
    String userLoggedIn;


    public String userCurrentlyLoggedIn() throws IOException { // bara läser för att kolla vem som är inloggad.
        try {
            FileReader fr = new FileReader("src/main/userLoggedIn.json");
            userLoggedInJson = new Gson().fromJson(fr, new TypeToken<>() { // kolla om vi behöver något inom <>
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (userLoggedInJson == null) {
                //System.out.println("userLoggedInJson = null"); // test ta bort sen
            } else {
                //System.out.println("userLoggedInJson = not empty"); // test ta bort sen
                userLoggedIn = userLoggedInJson;
            }
            System.out.println("User currently logged in: " + userLoggedIn); // behåller denna så du kan se hur den automatiskt byter mellan användare varje gånge du loggar ut och tillbaka in.
            fr.close();
        } catch (Exception e) {
            System.out.println("No users found");

        }
        return userLoggedIn;
    }

    public void updateUserLoggedIn(String userName, String password) throws IOException { // har ingen reader, för att jag vill alltid skriva över den gamla userna som var inloggad med en ny user.
        String user = userName + password;
        FileWriter fw = new FileWriter("src/main/userLoggedIn.json");
        gson.toJson(user, fw);
        fw.close();
    }



}
