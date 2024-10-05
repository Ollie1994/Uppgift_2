package BudgetApp;

import java.time.LocalDateTime;

public class Income_TemplateClass extends Transaction_TemplateClass {

    private IncomeCategory_EnumClass category;

    public Income_TemplateClass(double amount, LocalDateTime date, IncomeCategory_EnumClass category) {
        super(amount, date);
        this.category = category;
    }







    public IncomeCategory_EnumClass getCategory() {return category;}
    public void setCategory(IncomeCategory_EnumClass category) {this.category = category;}
}
