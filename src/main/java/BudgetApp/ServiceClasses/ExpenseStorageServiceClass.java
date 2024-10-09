package BudgetApp.ServiceClasses;
// https://stackoverflow.com/questions/43117731/what-is-type-typetoken
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
// https://bito.ai/resources/java-localdatetime-to-string-java-explained/#5
import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.TemplateClasses.Expense;
import BudgetApp.UserInputClass;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ExpenseStorageServiceClass {
    UserInputClass userInputClass = new UserInputClass();
    HashMap<LocalDateTime, Expense> expenses = new HashMap<LocalDateTime, Expense>();

    /* expense:
    - Amount, date, category

     */
    public void createExpense() {
        System.out.println("Pick a category");
        ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice()); // byt till annan tC
        System.out.println("Please enter an amount: ");
        double amount = userInputClass.inputAmountChoice();
        LocalDateTime date = LocalDateTime.now();
        expenses.put(LocalDateTime.now(), new Expense(amount, date, category));
        System.out.println("New " + category + " expense has been created\n For: " + amount + ".Kr\n Date: " + date);
    }




// overide methods from trans som skirver ut kategory amount och all annat





}
