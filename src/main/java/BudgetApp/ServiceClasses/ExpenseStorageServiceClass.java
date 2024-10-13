package BudgetApp.ServiceClasses;
// https://stackoverflow.com/questions/43117731/what-is-type-typetoken
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
// https://bito.ai/resources/java-localdatetime-to-string-java-explained/#5

import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.TemplateClasses.Expense;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ExpenseStorageServiceClass {
    UserInputClass userInputClass = new UserInputClass();
    HashMap<String, Expense> expenses = new HashMap<String, Expense>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();





    public void createExpense() throws IOException {
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();





        FileWriter fw = new FileWriter("src/main/expense.json"); // kanske fixar detta så det blir mer specifikt till månad eller user



        System.out.println("Pick a category");
        ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice()); // byt till annan tC
        System.out.println("Please enter an amount: ");
        double amount = userInputClass.inputAmountChoice();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtmn = ldt.minusNanos(100);
        String str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        expenses.put(str, new Expense(amount, category));
        gson.toJson(expenses, fw);
        fw.close();



       // SKRIV OM ---- System.out.println("New " + category + " expense has been created\n For: " + amount + ".Kr\n Date: " + date);
    }




// overide methods from trans som skirver ut kategory amount och all annat





}
