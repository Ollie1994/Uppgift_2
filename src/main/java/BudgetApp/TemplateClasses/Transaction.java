package BudgetApp.TemplateClasses;

import java.time.LocalDateTime;

public class Transaction {

    private double amount;
    private LocalDateTime date; // kanske ta bort ifall nyckel date trackar genom ta bort o lägg till i databas

    public Transaction(double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
        //lägga till mer här ? tex if august save in august ?
    }




    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}
}
