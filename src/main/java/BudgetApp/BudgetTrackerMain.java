package BudgetApp;

import BudgetApp.ServiceClasses.ExpenseStorageServiceClass;
import BudgetApp.ServiceClasses.UserServiceClass;

import java.io.IOException;

public class BudgetTrackerMain {
    public static void main(String[] args) throws IOException {
        UserInputClass userInputClass = new UserInputClass();
        UserServiceClass userServiceClass = new UserServiceClass();
        ExpenseStorageServiceClass expenseStorageServiceClass = new ExpenseStorageServiceClass();



        userServiceClass.deleteAccount();
        boolean loggedIn = false;
        while (loggedIn == false) {
            System.out.println("Start Menu\n1. Create a new account\n2. Login\n3. Delete an account\n4. Exit");
            int startMenu = userInputClass.inputStartMenuChoice();
            switch (startMenu) {
                case 1:
                    userServiceClass.createAccount();
                    break;
                case 2:
                    loggedIn = userServiceClass.login();
                    //System.out.println(loggedIn); // test ta bort sen
                    break;
                case 3:
                    userServiceClass.deleteAccount();
                case 4:
                    System.exit(0);

            }
        }
        //while loop så länge man är inloggad

        while (loggedIn == true) {
            //MENY 2
            System.out.println("\nMain Menu\n1. Back to start menu\n2. Display all accounts (test val)\n3. Add an expense" +
                    "\n4.----- \n5. Add an income\n6. --------  ");
            int mainMenu = userInputClass.inputStartMenuChoice(); // skicka in något här som ändra trycatchen till att tilåta flera val
            switch (mainMenu) {
                case 1 -> loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut?
                case 2 -> userServiceClass.displayAccounts(); // bara för test
                case 3 -> expenseStorageServiceClass.createExpense();
                //case 4 ->
                //case 5 ->


            }

        }
    }
}




