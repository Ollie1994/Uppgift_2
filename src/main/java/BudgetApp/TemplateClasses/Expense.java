package BudgetApp.TemplateClasses;

import BudgetApp.EnumClasses.ExpenseCategory;

import java.time.LocalDateTime;

public class Expense extends Transaction {

    private ExpenseCategory category;

    public Expense(double amount, LocalDateTime date, ExpenseCategory category) {
        super(amount, date);
        this.category = category;
    }


        // Skapa en metod för att displaya alla expenses (osv) som overrider från transaction
        // "så samma metod men tar med all ifrån trans och expense" osv

    public ExpenseCategory getCategory() {return category;}
    public void setCategory(ExpenseCategory category) {this.category = category;}
}
