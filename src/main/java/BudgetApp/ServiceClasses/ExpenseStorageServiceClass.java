package BudgetApp.ServiceClasses;
// https://stackoverflow.com/questions/43117731/what-is-type-typetoken
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
// https://bito.ai/resources/java-localdatetime-to-string-java-explained/#5
// https://www.geeksforgeeks.org/hashmap-replacekey-value-method-in-java-with-examples/


import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.TemplateClasses.Expense;
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

public class ExpenseStorageServiceClass {
    UserInputClass userInputClass = new UserInputClass();
    HashMap<String, Expense> expenses = new HashMap<String, Expense>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UserServiceClass userServiceClass = new UserServiceClass();
    HashMap<String, Expense> expensesJson = new HashMap<String, Expense>();
    HashMap<String, Expense> allExpenses = new HashMap<String, Expense>();
    HashMap<String, Expense> allExpensesJson = new HashMap<String, Expense>();








    //------------------ METHODS ---------------------------------------------------------------


    public void addExpenseToAllExpensesList(String userName, String password, ExpenseCategory category, String date, double amount) throws IOException {
        try {
            FileReader fr = new FileReader("src/main/userSpecificFiles/" + userName + password + "/" + "allExpenses.json");
            allExpensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            if (allExpensesJson == null) {
                System.out.println("allExpensesJson = null"); // test ta bort sen
            } else {
                System.out.println("allExpensesJson = not empty"); // test ta bort sen
                allExpenses = allExpensesJson;
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("catch ?");
        }
        allExpenses.put(date, new Expense(amount, category));
        System.out.println("You have successfully created a new expense with category " + category + " and amount " + amount);
        FileWriter fw = new FileWriter("src/main/userSpecificFiles/" + userName + password + "/" + "allExpenses.json");
        gson.toJson(allExpenses, fw);
        fw.close();
    }

    public void createExpense() throws IOException {

        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Pick a category");
        ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice()); // byt till annan tC
        System.out.println("Please enter an amount: ");
        double amount = userInputClass.inputAmountChoice();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtmn = ldt.minusNanos(100);
        String str = "";
        String path = "";

        System.out.println("Would you like to create a custom date for the expense?(yes/no).");
        String yesNoChoice = userInputClass.inputUsernamePasswordDateChoice();

        if (yesNoChoice.equals("yes")) {
            System.out.println("Type in the custom date (yyyy-MM-dd HH:mm:ss)");
            String createdDate = userInputClass.inputUsernamePasswordDateChoice();
            str = createdDate;

            String yearSYes = str;
            String monthSYes = str;
            StringBuilder yearSb = new StringBuilder(yearSYes);
            yearSb.setLength(4);
            yearSYes = yearSb.toString();
            System.out.println(yearSYes);

            StringBuilder monthSb = new StringBuilder(monthSYes);
            monthSb.setLength(7);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSYes = monthSb.toString();
            System.out.println(monthSYes);

            System.out.println("You have created this new date: " + createdDate + ", year: " + yearSYes + ", month: " + monthSYes);

            path = "src/main/userSpecificFiles/" + username + password + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";
            File test = new File("src/main/userSpecificFiles/" + username + password + "/" + yearSYes + "/" + monthSYes);
            test.mkdirs();

        } else {

            str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String pathStr = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));

            String yearSNo = ldt.format(DateTimeFormatter.ofPattern("yyyy"));
            String monthSNo = ldt.format(DateTimeFormatter.ofPattern("MM"));


            path = "src/main/userSpecificFiles/" + username + password + "/" + yearSNo + "/" + monthSNo + "/" + "Expenses.json";
            File test = new File("src/main/userSpecificFiles/" + username + password + "/" + yearSNo + "/" + monthSNo);
            test.mkdirs();
        }

        try {
            FileReader fr = new FileReader(path);
            expensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            if (expensesJson == null) {
                System.out.println("expensesJson = null"); // test ta bort sen
            } else {
                System.out.println("expensesJson = not empty"); // test ta bort sen
                expenses = expensesJson;
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("catch ?");
        }


        expenses.put(str, new Expense(amount, category));
        System.out.println("You have successfully created a new expense with category " + category + " and amount " + amount);
        FileWriter fw = new FileWriter(path);
        gson.toJson(expenses, fw);
        fw.close();
        addExpenseToAllExpensesList(username, password, category, str, amount); // lägger till expenses till en ALL expense fil.
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public boolean displayExpensesByDate() throws IOException { // bara för testing
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the expenses you would like to checkout, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        System.out.println(monthSYes);

        String path = "src/main/userSpecificFiles/" + username + password + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";

        boolean expensesFound = true;
        try {
            FileReader fr = new FileReader(path);
            expensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            if (expensesJson == null) {
                System.out.println("expensesJson = null"); // test ta bort sen
                expensesFound = false;
            } else {
                System.out.println("expensesJson = not empty"); // test ta bort sen
                expenses = expensesJson;
            }
            for (String i : expenses.keySet()) {
                System.out.println("_______________________\nKey - " + i + "\n" + expenses.get(i));
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");
            expensesFound = false;
        }
        return expensesFound; // ska returna till remove expenses och ändra expenses
    }

    public boolean displayAllExpenses(boolean choice) throws IOException { // bara för testing
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        String path = "src/main/userSpecificFiles/" + username + password + "/" +  "allExpenses.json";

        boolean expensesFound = true;
        try {
            FileReader fr = new FileReader(path);
            allExpensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            if (allExpensesJson == null) {
                System.out.println("allExpensesJson = null"); // test ta bort sen
                expensesFound = false;
            } else {
                System.out.println("allExpensesJson = not empty"); // test ta bort sen
                allExpenses = allExpensesJson;
            }
            if (choice) {
                for (String i : allExpenses.keySet()) {
                    System.out.println("_______________________\nKey - " + i + "\n" + allExpenses.get(i));
                }
            }
            else {
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");
            expensesFound = false;
        }
        return expensesFound; // ska returna till remove expenses och ändra expenses
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public void deleteAnExpenseFromAllExpensesList(String userName, String password, String date) throws IOException {
        String path = "src/main/userSpecificFiles/" + userName + password + "/" + "allExpenses.json";
        boolean expensesFound = displayAllExpenses(false);

        if (expensesFound) {
            allExpenses.remove(date);
            FileWriter fw = new FileWriter(path);
            gson.toJson(allExpenses, fw);
            fw.close();
        } else {
            System.out.println("No expenses found");
        }
    }

    public void deleteAnExpenseByDate() throws IOException {
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the expense you would like to remove, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        System.out.println(monthSYes);

        String path = "src/main/userSpecificFiles/" + username + password + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";

        boolean expensesFound = displayExpensesByDate();

        if (expensesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of expense you want to remove");
            String expenseToBeRemoved = userInputClass.inputUsernamePasswordDateChoice();
            System.out.println(expenses.get(expenseToBeRemoved) + "\n // Has been removed!");
            expenses.remove(expenseToBeRemoved);
            FileWriter fw = new FileWriter(path);
            gson.toJson(expenses, fw);
            fw.close();
            deleteAnExpenseFromAllExpensesList(username, password, expenseToBeRemoved);
        } else {
            System.out.println("No expenses found");
        }
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public void updateAnExpenseByDate() throws IOException {
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the expense you would like to update, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();
        String path = "src/main/userSpecificFiles/" + username + password + "/" + username + password + date + "/" + username + password + date + "Expenses.json";

        boolean expensesFound = displayExpensesByDate();

        if (expensesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of expense you want to update");
            String expenseToBeUpdated = userInputClass.inputUsernamePasswordDateChoice();
            System.out.println("Pick a category");
            ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice()); // byt till annan tC
            System.out.println("Please enter an amount: ");
            double amount = userInputClass.inputAmountChoice();

            System.out.println("Would you also like to change the date of the expense yes/no? (You can only change it within the month)");
            String answer = userInputClass.inputUsernamePasswordDateChoice();
            if (answer.equals("yes")) {
                System.out.println("Type in the new date - yyyy-MM-dd HH:mm:ss");
                String newDate = userInputClass.inputUsernamePasswordDateChoice();
                System.out.println(expenses.get(expenseToBeUpdated) + ", Date: " + expenseToBeUpdated + "\n // Has been replace with ->");
                expenses.remove(expenseToBeUpdated);
                expenses.put(newDate, new Expense(amount, category));
                System.out.println("Updated " + expenses.get(newDate) + ", New date: " + newDate);
            } else {
                System.out.println("The original date is kept");
                System.out.println(expenses.get(expenseToBeUpdated) + "\n // Has been replace with ->");
                expenses.replace(expenseToBeUpdated, new Expense(amount, category));
                System.out.println("Updated " + expenses.get(expenseToBeUpdated));
            }



            FileWriter fw = new FileWriter(path);
            gson.toJson(expenses, fw);
            fw.close();
        } else {
            System.out.println("No expenses found");
        }
    }









// overide methods from trans som skirver ut kategory amount och all annat





}
