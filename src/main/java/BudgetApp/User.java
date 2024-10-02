package BudgetApp;

import java.util.HashMap;

public class User {

   // private String name;
   // private String lastName; // FRÅGA HELENA
    private String userName; // optional
    private String password; // optional ifall vi lägger till logga in

    TryCatch tryCatch = new TryCatch();

    HashMap<String, User> users = new HashMap<>();


    public User(String userName, String password) { // vill jag skapa en ny lista varje gång ??? vill jag ha accounts listan i konstruktorn???
        this.userName = userName;
        this.password = password;
    }


    public void createAccount() {
        String userName = tryCatch.TryCatchUsername(); // var mycket lättare att implemetera 2 trycatch istället för 1
        String password = tryCatch.TryCatchPassword();
        users.put("1", new User(userName, password)); // fixa så att 1an uppdateras med +1 varjegång vi skapar en ny user -
        // genom att kolla om 1 redan finns
        displayAccounts();
    }


    public void displayAccounts() {
        for (User user : users.values()) {
            System.out.println(user);
            System.out.println();
        }
    }















    @Override
    public String toString() {
        return "\nUser\n" + " - Username: " + userName + "\n - Password: " + password + "\n_________________________________";}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
