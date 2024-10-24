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
        while (loggedIn == false) { // start meny där man kan logga in, skapa konto och avsluta programmet.
            System.out.println("Start Menu\n1. Exit\n2. Login\n3. Create a new account");
            int startMenu = userInputClass.inputStartMenuChoice();
            switch (startMenu) {
                case 1:
                    System.exit(0);
                    break;
                case 2:
                    loggedIn = userServiceClass.login();
                    break;
                case 3:
                    userServiceClass.createAccount();
                    break;
            }


            while (loggedIn == true) {  //while loop så länge man är inloggad // och huvudmeny där man kan göra allt som budgetprogrammet ska göra
                //MENY 2
                System.out.println(
                        "\nMain Menu\n" +
                                "1. Back to start menu\n" +
                                "----------------------------------------------------------------------\n" +
                                "2. Add an expense\n" +
                                "3. Add an income\n" +
                                "----------------------------------------------------------------------\n" +
                                "4. Display all expenses\n" +
                                "5. Display all incomes\n" +
                                "6. Calculate total for all expenses\n" +
                                "7. Calculate total for all incomes\n" +
                                "8. Calculate total of all incomes minus all expenses\n" +
                                "----------------------------------------------------------------------\n" +
                                "9. Display expenses by date\n" +
                                "10. Display incomes by date\n" +
                                "11. Delete an expense by date\n" +
                                "12. Delete an income by date\n" +
                                "13. Update an expense by date\n" +
                                "14. Update an income by date\n" +
                                "15. Search for a specific expense by category and amount\n" +
                                "16. Search for a specific income by category and amount\n" +
                                "17. Calculate total for all expenses by selected month\n" +
                                "18. Calculate total for all incomes by selected month\n" +
                                "19. Calculate total of all incomes minus all expenses per selected month\n" +
                                "----------------------------------------------------------------------\n");

                int mainMenu = userInputClass.inputMainMenuChoice(); // skicka in något här som ändra trycatchen till att tilåta flera val
                switch (mainMenu) {
                    case 1:
                        loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut.
                        break;
                    case 2:
                        expenseStorageServiceClass.createExpense();
                        break;
                    case 3:
                        incomeStorageServiceClass.createIncome();
                        break;
                    case 4:
                        expenseStorageServiceClass.displayAllExpenses(true);
                        break;
                    case 5:
                        incomeStorageServiceClass.displayAllIncomes(true);
                        break;
                    case 6:
                        expenseStorageServiceClass.calculateTotalForAllExpenses();
                        break;
                    case 7:
                        incomeStorageServiceClass.calculateTotalForAllIncomes();
                        break;
                    case 8:
                        double incTotal1 = incomeStorageServiceClass.calculateTotalForAllIncomes();
                        double expTotal1 = expenseStorageServiceClass.calculateTotalForAllExpenses();
                        double incMinusExp1 = incTotal1 - expTotal1;
                        System.out.println("The total for all incomes minus all expenses are: " + incMinusExp1);
                        break;
                    case 9:
                        System.out.println("Enter the year and month of the expenses you would like to checkout, (yyyyMM/199408)");
                        String date = userInputClass.inputUsernamePasswordDateChoice();
                        expenseStorageServiceClass.displayExpensesByDate(date);
                        break;
                    case 10:
                        System.out.println("Enter the year and month of the incomes you would like to checkout, (yyyyMM/199408)");
                        String date2 = userInputClass.inputUsernamePasswordDateChoice();
                        incomeStorageServiceClass.displayIncomesByDate(date2);
                        break;
                    case 11:
                        expenseStorageServiceClass.deleteAnExpenseByDate();
                        break;
                    case 12:
                        incomeStorageServiceClass.deleteAnIncomeByDate();
                        break;
                    case 13:
                        expenseStorageServiceClass.updateAnExpenseByDate();
                        break;
                    case 14:
                        incomeStorageServiceClass.updateAnIncomeByDate();
                        break;
                    case 15:
                        expenseStorageServiceClass.searchForSpecificExpenseByCategoryAndAmount();
                        break;
                    case 16:
                        incomeStorageServiceClass.searchForSpecificIncomeByCategoryAndAmount();
                        break;
                    case 17:
                        System.out.println("Enter the year and month of the expenses you would like the total of, (yyyyMM/199408)");
                        String date3 = userInputClass.inputUsernamePasswordDateChoice();
                        expenseStorageServiceClass.calculateTotalForAllExpensesByMonth(date3);
                        break;
                    case 18:
                        System.out.println("Enter the year and month of the incomes you would like the total of, (yyyyMM/199408)");
                        String date4 = userInputClass.inputUsernamePasswordDateChoice();
                        incomeStorageServiceClass.calculateTotalForAllIncomesByMonth(date4);
                        break;
                    case 19:
                        System.out.println("Enter the year and month of the month you want the incomes minus expenses you would like the total of, (yyyyMM/199408)");
                        String date5 = userInputClass.inputUsernamePasswordDateChoice();
                        double incTotal2 = incomeStorageServiceClass.calculateTotalForAllIncomesByMonth(date5);
                        double expTotal2 = expenseStorageServiceClass.calculateTotalForAllExpensesByMonth(date5);
                        double incMinusExp2 = incTotal2 - expTotal2;
                        System.out.println("The total for incomes minus expenses are: " + incMinusExp2);
                        break;
                }

            }
        }
    }
}




