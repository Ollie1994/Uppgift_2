package BudgetApp;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ExpenseStorage_Sc {
    TryCatch_Mc tryCatch_Mc = new TryCatch_Mc();
    HashMap<LocalDateTime, Expense_Tc> expenses = new HashMap<LocalDateTime, Expense_Tc>();

    /* expense:
    - Amount, date, category

     */
    public void createExpense() {
        System.out.println("Pick a category");
        ExpenseCategory_Ec category = ExpenseCategory_Ec.valueOf(tryCatch_Mc.TryCatch4()); // byt till annan tC
        System.out.println("Please enter an amount: ");
        double amount = tryCatch_Mc.TryCatch3();
        LocalDateTime date = LocalDateTime.now();
        expenses.put(LocalDateTime.now(), new Expense_Tc(amount, date, category));
        System.out.println("New " + category + " expense has been created\n For: " + amount + ".Kr\n Date: " + date);
    }




// overide methods from trans som skirver ut kategory amount och all annat





}
