package BudgetApp;

import java.time.LocalDateTime;

public class Income_Tc extends Transaction_Tc {

    private IncomeCategory_Ec category;

    public Income_Tc(double amount, LocalDateTime date, IncomeCategory_Ec category) {
        super(amount, date);
        this.category = category;
    }







    public IncomeCategory_Ec getCategory() {return category;}
    public void setCategory(IncomeCategory_Ec category) {this.category = category;}
}
