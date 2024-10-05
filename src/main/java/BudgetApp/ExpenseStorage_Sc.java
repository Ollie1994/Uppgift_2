package BudgetApp;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ExpenseStorage_Sc {
    TryCatch_Mc tryCatch_Mc = new TryCatch_Mc();
    HashMap<LocalDateTime, Expense_Tc> expense_Tc = new HashMap<LocalDateTime, Expense_Tc>();

    /* expense:
    - Amount, date, category

     */
    public void createExpense() {
        expense_Tc.put(LocalDateTime.now(), new Expense_Tc(1000, LocalDateTime.now(), ExpenseCategory_Ec.CHARITY )); // fixa så att 1an uppdateras med +1 varjegång vi skapar en ny user -

    }










}
