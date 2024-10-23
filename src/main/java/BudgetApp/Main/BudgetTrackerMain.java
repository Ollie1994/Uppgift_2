package BudgetApp.Main;

import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.ServiceClasses.ExpenseStorageServiceClass;
import BudgetApp.ServiceClasses.IncomeStorageServiceClass;
import BudgetApp.ServiceClasses.UserServiceClass;

import java.io.IOException;

public class BudgetTrackerMain {
    public static void main(String[] args) throws IOException {
        UserInputClass userInputClass = new UserInputClass();
        UserServiceClass userServiceClass = new UserServiceClass();
        ExpenseStorageServiceClass expenseStorageServiceClass = new ExpenseStorageServiceClass();
        IncomeStorageServiceClass incomeStorageServiceClass = new IncomeStorageServiceClass();



        boolean loggedIn = false;
        while (loggedIn == false) {
            System.out.println("Start Menu\n1. Exit\n2. Login\n3. Create a new account");
            int startMenu = userInputClass.inputStartMenuChoice();
            switch (startMenu) {
                case 1:
                    System.exit(0);
                    break;
                    //userServiceClass.createAccount();
                    //break;
                case 2:
                    loggedIn = userServiceClass.login();
                    //System.out.println(loggedIn); // test ta bort sen
                    break;
                case 3:
                    //userServiceClass.deleteAccount(); // denna bara för admin / test
                    //break;
                    userServiceClass.createAccount();
                    break;
                //case 4:
                    //System.exit(0);
                    //break;

            }

            //while loop så länge man är inloggad


            while (loggedIn == true) {
                //MENY 2
                System.out.println("\nMain Menu\n1. Back to start menu\n2. Display all accounts (test val)\n3. Add an expense" +
                        "\n4. Display expenses by date\n5. Delete an expense by date\n6. Update an expense by date\n7. Display all expenses" +
                        "\n8. Search for e specific expense by category and amount\n9. Add an income" +
                        "\n10. Display incomes by date\n11. Delete an income by date\n12. Update an income by date" +
                        "\n13. Display all incomes\n14. Search for e specific income by category and amount" +
                        "\n15. Calculate total for all expenses\n16. Calculate total for all incomes");

                int mainMenu = userInputClass.inputMainMenuChoice(); // skicka in något här som ändra trycatchen till att tilåta flera val
                switch (mainMenu) {
                    case 1:
                        loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut?
                        break;
                    case 2:
                        userServiceClass.displayAccounts(); // bara för admin / test
                        break;
                    case 3:
                        expenseStorageServiceClass.createExpense();
                        break;
                    case 4:
                        System.out.println("Enter the year and month of the expenses you would like to checkout, (yyyyMM/199408)");
                        String date = userInputClass.inputUsernamePasswordDateChoice();
                        expenseStorageServiceClass.displayExpensesByDate(date);
                        break;
                    case 5:
                        expenseStorageServiceClass.deleteAnExpenseByDate();
                        break;
                    case 6:
                        expenseStorageServiceClass.updateAnExpenseByDate();
                        break;
                    case 7:
                        expenseStorageServiceClass.displayAllExpenses(true);
                        break;
                    case 8:
                        expenseStorageServiceClass.searchForSpecificExpenseByCategoryAndAmount();
                        break;
                    case 9:
                        incomeStorageServiceClass.createIncome();
                        break;
                    case 10:
                        System.out.println("Enter the year and month of the incomes you would like to checkout, (yyyyMM/199408)");
                        String date2 = userInputClass.inputUsernamePasswordDateChoice();
                        incomeStorageServiceClass.displayIncomesByDate(date2);
                        break;
                    case 11:
                        incomeStorageServiceClass.deleteAnIncomeByDate();
                        break;
                    case 12:
                        incomeStorageServiceClass.updateAnIncomeByDate();
                        break;
                    case 13:
                        incomeStorageServiceClass.displayAllIncomes(true);
                        break;
                    case 14:
                        incomeStorageServiceClass.searchForSpecificIncomeByCategoryAndAmount();
                        break;
                    case 15:
                        expenseStorageServiceClass.calculateTotalForAllExpenses();
                        break;
                    case 16:
                        incomeStorageServiceClass.calculateTotalForAllIncomes();
                        break;





                }

            }
        }
    }
}




