package main.model.accounts;

public class Savings extends Account {

    public Savings(String id, String name, double balance) {
        super(Type.Savings, id, name, balance);
    }

    public Savings(Savings source) {
        super(source);
    }

    @Override
    public Account clone() {
        return new Savings(this);
    }

    @Override
    public boolean withdraw(double amount) {
        if(super.getBalance() - (amount + 5) >= 0) {
            super.setBalance(round(super.getBalance() - (amount + 5)));
            return true;
        }

        else {
            System.out.println("Unable to withdraw. Insufficient funds available.");
            return false;
        }
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(round(super.getBalance() + amount));
    }
}
