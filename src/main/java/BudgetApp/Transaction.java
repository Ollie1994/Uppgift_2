package BudgetApp;

import java.time.LocalDate;

public class Transaction {

    private double amount;
    private LocalDate date;

    public Transaction(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
        //lägga till mer här ? tex if august save in august ?
    }










    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
}
