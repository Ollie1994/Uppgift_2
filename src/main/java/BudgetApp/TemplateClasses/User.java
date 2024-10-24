package BudgetApp.TemplateClasses;

public class User {

   // private String name;
   // private String lastName;
    private String userName;
    private String password;
    // använda username + password istället för name + lastname för det verkade mer "riktigt" och användbart. plus det påvisar samma kunskaper endå.




    public User(String userName, String password) { // vill jag skapa en ny lista varje gång ??? vill jag ha accounts listan i konstruktorn???
        this.userName = userName;
        this.password = password;
    }



    @Override
    public String toString() {
        return "Username: " + userName  + ", Password: " +  password + "\n" + "________________________________________________\n";
    }

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
