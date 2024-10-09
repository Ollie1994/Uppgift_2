package BudgetApp;

import BudgetApp.ServiceClasses.ExpenseStorageServiceClass;
import BudgetApp.ServiceClasses.UserServiceClass;

import java.io.IOException;

public class BudgetTrackerMain {
    public static void main(String[] args) throws IOException {
        UserInputClass userInputClass = new UserInputClass();
        UserServiceClass userServiceClass = new UserServiceClass();
        ExpenseStorageServiceClass expenseStorageServiceClass = new ExpenseStorageServiceClass();


        boolean loggedIn = false;
        while (loggedIn == false) {
            System.out.println("Start Menu\n1. Create a new account\n2. Login\n3. Exit");
            int startMenu = userInputClass.inputStartMenuChoice();
            switch (startMenu) {
                case 1:
                    userServiceClass.createAccount();
                    break;
                case 2:
                    loggedIn = userServiceClass.login();
                    System.out.println(loggedIn); // test ta bort sen
                    break;

                case 3:
                    System.exit(0);

            }
        }
        //while loop så länge man är inloggad

        while (loggedIn == true) {
            //MENY 2
            System.out.println("YOU ARE LOGGED IN!\nMain Menu\n1. Display all accounts (test val)\n2. EXIT!" +
                    "\n3. Add an expense\n4. Add an income\n5. ");
            int mainMenu = userInputClass.inputStartMenuChoice(); // skicka in något här som ändra trycatchen till att tilåta flera val
            switch (mainMenu) {
                case 1 -> userServiceClass.displayAccounts(); // bara för test
                case 2 -> loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut?
                case 3 -> expenseStorageServiceClass.createExpense();


            }

        }
    }
}




