package BudgetApp;

import java.time.LocalDateTime;

public class Transaction_TemplateClass {

    private double amount;
    private LocalDateTime date;

    public Transaction_TemplateClass(double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
        //lägga till mer här ? tex if august save in august ?
    }










    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}
}
