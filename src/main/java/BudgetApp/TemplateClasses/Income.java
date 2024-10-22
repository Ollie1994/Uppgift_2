package BudgetApp.TemplateClasses;

import BudgetApp.EnumClasses.IncomeCategory;

public class Income extends Transaction {

    private IncomeCategory category;

    public Income(double amount, IncomeCategory category) {
        super(amount);
        this.category = category;
    }


    @Override
    public String toString() {
        return "Income\n- Category: " + category + "\n- Amount: " + getAmount();
    }

    public IncomeCategory getCategory() {return category;}
    public void setCategory(IncomeCategory category) {this.category = category;}
}
