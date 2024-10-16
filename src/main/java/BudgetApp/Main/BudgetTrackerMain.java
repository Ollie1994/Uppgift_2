package BudgetApp.Main;

import BudgetApp.InputClasses.UserInputClass;
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
                    break;
                case 4:
                    System.exit(0);
                    break;

            }

            //while loop så länge man är inloggad

            /// LÄgg till mer i trycatch före att hantera större meny
            // gört ny trycatch för Main menu!!!!!
            while (loggedIn == true) {
                //MENY 2
                System.out.println("\nMain Menu\n1. Back to start menu\n2. Display all accounts (test val)\n3. Add an expense" +
                        "\n4. Display expenses by date\n5. Delete an expense by date\n6. Update an expense by date\n7. Display all expenses" +
                        "\n8. ------");
                int mainMenu = userInputClass.inputMainMenuChoice(); // skicka in något här som ändra trycatchen till att tilåta flera val
                switch (mainMenu) {
                    case 1:
                        loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut?
                        break;
                    case 2:
                        userServiceClass.displayAccounts(); // bara för test
                        break;
                    case 3:
                        expenseStorageServiceClass.createExpense();
                        break;
                    case 4:
                        System.out.println("Please enter ur username");
                        String username = userInputClass.inputUsernamePasswordDateChoice();
                        System.out.println("Please enter your password");
                        String password = userInputClass.inputUsernamePasswordDateChoice();
                        System.out.println("Enter the year and month of the expenses you would like to checkout, (yyyyMM/199408)");
                        String date = userInputClass.inputUsernamePasswordDateChoice();
                        expenseStorageServiceClass.displayExpensesByDate(username, password, date);
                        break;
                    case 5:
                        expenseStorageServiceClass.deleteAnExpenseByDate();
                        break;
                    case 6:
                        expenseStorageServiceClass.updateAnExpenseByDate();
                        break;
                    case 7:
                        System.out.println("Please enter ur username");
                        String userName = userInputClass.inputUsernamePasswordDateChoice();
                        System.out.println("Please enter your password");
                        String passWord = userInputClass.inputUsernamePasswordDateChoice();
                        expenseStorageServiceClass.displayAllExpenses(true, userName, passWord);
                        break;
                        //case 8:




                }

            }
        }
    }
}




