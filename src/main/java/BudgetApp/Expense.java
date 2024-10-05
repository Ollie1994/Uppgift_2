package BudgetApp;

import java.time.LocalDateTime;

public class Expense extends Transaction {

    private ExpenseCategory category;

    public Expense(double amount, LocalDateTime date, ExpenseCategory category) {
        super(amount, date);
        this.category = category;
    }






    public ExpenseCategory getCategory() {return category;}
    public void setCategory(ExpenseCategory category) {this.category = category;}
}
