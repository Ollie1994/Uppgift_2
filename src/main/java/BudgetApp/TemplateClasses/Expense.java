package BudgetApp.TemplateClasses;

import BudgetApp.EnumClasses.ExpenseCategory;

public class Expense extends Transaction {

    private ExpenseCategory category;

    public Expense(double amount, ExpenseCategory category) {
        super(amount);
        this.category = category;
    }

    @Override
    public String toString() { // tror detta är en typ av compile-time polyformism
        return "Expense\n- Category: " + category + "\n- Amount: " + getAmount();
    }



    public ExpenseCategory getCategory() {return category;}
    public void setCategory(ExpenseCategory category) {this.category = category;}
}
