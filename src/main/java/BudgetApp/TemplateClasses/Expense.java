package BudgetApp.TemplateClasses;

import BudgetApp.EnumClasses.ExpenseCategory;

public class Expense extends Transaction {

    private ExpenseCategory category;

    public Expense(double amount, ExpenseCategory category) {
        super(amount);
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense\n- Category: " + category + "\n- Amount: " + getAmount();
    }

// Skapa en metod för att displaya alla expenses (osv) som overrider från transaction
        // "så samma metod men tar med all ifrån trans och expense" osv

    public ExpenseCategory getCategory() {return category;}
    public void setCategory(ExpenseCategory category) {this.category = category;}
}
