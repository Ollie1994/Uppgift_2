package BudgetApp;

public class User {

   // private String name;
   // private String lastName; // FRÅGA HELENA
    private String userName; // optional
    private String password; // optional ifall vi lägger till logga in




    public User(String userName, String password) { // vill jag skapa en ny lista varje gång ??? vill jag ha accounts listan i konstruktorn???
        this.userName = userName;
        this.password = password;
    }



    @Override
    public String toString() {
        return "\nUser\n" + " - Username: " + userName + "\n - Password: " + password + "\n_________________________________";}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
