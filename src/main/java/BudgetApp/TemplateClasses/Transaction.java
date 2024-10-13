package BudgetApp.TemplateClasses;

public class Transaction {

    private double amount;

    public Transaction(double amount) {
        this.amount = amount;
        //lägga till mer här ? tex if august save in august ?
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                '}';
    }

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
}
