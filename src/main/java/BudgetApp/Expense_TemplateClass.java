package BudgetApp;

import java.time.LocalDateTime;

public class Expense_TemplateClass extends Transaction_TemplateClass {

    private ExpenseCategory_EnumClass category;

    public Expense_TemplateClass(double amount, LocalDateTime date, ExpenseCategory_EnumClass category) {
        super(amount, date);
        this.category = category;
    }






    public ExpenseCategory_EnumClass getCategory() {return category;}
    public void setCategory(ExpenseCategory_EnumClass category) {this.category = category;}
}
