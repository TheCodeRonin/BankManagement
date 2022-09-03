package main.model.accounts;

public class Loan extends Account{

    public Loan(String id, String name, double balance) {
        super(Type.Loan, id, name, balance);
    }

    public Loan(Loan source) {
        super(source);
    }

    @Override
    public Account clone() {
        return new Loan(this);
    }

    @Override
    public boolean withdraw(double amount) {
        if(super.getBalance() + (amount * 1.02) > 10000) {
            System.out.println("Unable to withdraw. Threshold of debt ($10,000) cannot be exceeded.");
            return true;
        }

        else {
            super.setBalance(round(super.getBalance() + (amount * 1.02)));
            return false;
        }
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(round(super.getBalance() - amount));

        if(super.getBalance() < 0) {
            System.out.println("Loan balance is now 0. You will be refunded " + (super.getBalance() * -1) + ".");
            super.setBalance(0);
        }

        else if(super.getBalance() == 0){
            System.out.println("Loan balance is now 0.");
        }
    }
}
