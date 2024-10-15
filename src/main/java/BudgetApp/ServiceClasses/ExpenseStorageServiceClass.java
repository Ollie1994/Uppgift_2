package BudgetApp.ServiceClasses;
// https://stackoverflow.com/questions/43117731/what-is-type-typetoken
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
// https://bito.ai/resources/java-localdatetime-to-string-java-explained/#5

import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.TemplateClasses.Expense;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
        String str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String pathStr = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));
        String path = "src/main/userSpecificFiles/" + username + password + "/" + username + password + pathStr + "/" + username + password + pathStr + "Expenses.json";


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
        System.out.println("Enter the year and month of the expense you would like to checkout, (yyyyMM/199408)");
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













// overide methods from trans som skirver ut kategory amount och all annat





}
