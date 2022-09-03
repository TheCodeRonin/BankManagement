package main.model.accounts;
import main.model.accounts.impl.Taxable;

public class Checking extends Account implements Taxable {
    private static final double TAXABLE_INCOME = 3000;
    private static final double TAX_RATE = 0.15;

    public Checking(String id, String name, double balance) {
        super(Type.Checking, id, name, balance);
    }

    public Checking(Checking source) {
        super(source);
    }

    @Override
    public Account clone() {
        return new Checking(this);
    }

    @Override
    public boolean withdraw(double amount) {
        if(super.getBalance() - amount < -200) {
            System.out.println("Unable to withdraw. Insufficient funds available.");
            return false;
        }

        else if(super.getBalance() - amount > -200 && super.getBalance() - amount < 0) {
            super.setBalance(round(super.getBalance() - (amount + 5.5)));
            return true;
        }

        else {
            super.setBalance(round(super.getBalance() - amount));
            return true;
        }
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(round(super.getBalance() + amount));
    }

    @Override
    public void tax(double income) {
        double tax = Math.max(0, income - TAXABLE_INCOME) * TAX_RATE;

        super.setBalance(round(super.getBalance() - tax));
    }
}
