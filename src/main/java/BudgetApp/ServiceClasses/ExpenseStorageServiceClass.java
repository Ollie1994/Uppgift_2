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









    //------------------ METHODS ---------------------------------------------------------------

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


        System.out.println("Would you like to use custom date for the expense?(yes/no).");
        String yesNoChoice = userInputClass.inputUsernamePasswordDateChoice();

        if (yesNoChoice.equals("yes")) {
            System.out.println("Type in the custom date (yyyy-MM-dd HH:mm:ss)");
            String createdDate = userInputClass.inputUsernamePasswordDateChoice();
            str = createdDate;
            String strPath = str;
            StringBuilder strB = new StringBuilder(strPath);
            System.out.println(strB.toString());
            strB.setLength(7);
            System.out.println(strB.toString());
            strB.deleteCharAt(strB.length() -3);
            System.out.println(strB.toString());;
            strPath = strB.toString();
            System.out.println(strPath);
            System.out.println("You have created this new date: " + createdDate + ", path: " + strPath);
            path = "src/main/userSpecificFiles/" + username + password + "/" + username + password + strPath + "/" + username + password + strPath + "Expenses.json";
            File test = new File("src/main/userSpecificFiles/" + username + password + "/" + username + password + strPath);
            test.mkdirs();

        } else {

            str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String pathStr = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));
            path = "src/main/userSpecificFiles/" + username + password + "/" + username + password + pathStr + "/" + username + password + pathStr + "Expenses.json";
            File test = new File("src/main/userSpecificFiles/" + username + password + "/" + username + password + pathStr);
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
    }



    public boolean displayExpensesByDate() throws IOException { // bara för testing
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the expenses you would like to checkout, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();
        String path = "src/main/userSpecificFiles/" + username + password + "/" + username + password + date + "/" + username + password + date + "Expenses.json";

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


    public void deleteAnExpenseByDate() throws IOException {
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the expense you would like to remove, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();
        String path = "src/main/userSpecificFiles/" + username + password + "/" + username + password + date + "/" + username + password + date + "Expenses.json";

        boolean expensesFound = displayExpensesByDate();

        if (expensesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of expense you want to remove");
            String expenseToBeRemoved = userInputClass.inputUsernamePasswordDateChoice();
            System.out.println(expenses.get(expenseToBeRemoved) + "\n // Has been removed!");
            expenses.remove(expenseToBeRemoved);
            FileWriter fw = new FileWriter(path);
            gson.toJson(expenses, fw);
            fw.close();
        } else {
            System.out.println("No expenses found");
        }
    }


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
