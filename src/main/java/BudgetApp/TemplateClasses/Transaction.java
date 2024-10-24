package BudgetApp.TemplateClasses;

public class Transaction {

    private double amount;
    // tog bort "date" härifrån och la den i nyckeln till HashMappen istället.
    // den funkar bättre i nyckeln tycker jag då LDT.now skapar en unik nyckel vilket gör det smidigare att hantera.

    public Transaction(double amount) {
        this.amount = amount;

    }

    @Override
    public String toString() { // tror detta är en typ av compile-time polyformism
        return "Transaction{" +
                "amount=" + amount +
                '}';
    }

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
}
