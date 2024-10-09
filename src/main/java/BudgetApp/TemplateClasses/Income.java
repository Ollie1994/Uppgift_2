package BudgetApp.TemplateClasses;

import BudgetApp.BudgetTrackerMain;
import BudgetApp.EnumClasses.IncomeCategory;

import java.time.LocalDateTime;

public class Income extends BudgetTrackerMain.Transaction {

    private IncomeCategory category;

    public Income(double amount, LocalDateTime date, IncomeCategory category) {
        super(amount, date);
        this.category = category;
    }







    public IncomeCategory getCategory() {return category;}
    public void setCategory(IncomeCategory category) {this.category = category;}
}
