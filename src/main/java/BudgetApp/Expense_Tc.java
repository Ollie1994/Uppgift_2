package BudgetApp;

import java.time.LocalDateTime;

public class Expense_Tc extends Transaction_Tc {

    private ExpenseCategory_Ec category;

    public Expense_Tc(double amount, LocalDateTime date, ExpenseCategory_Ec category) {
        super(amount, date);
        this.category = category;
    }




    public ExpenseCategory_Ec getCategory() {return category;}
    public void setCategory(ExpenseCategory_Ec category) {this.category = category;}
}
