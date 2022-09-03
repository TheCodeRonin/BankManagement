package main.model.accounts;

import java.text.DecimalFormat;

public abstract class Account{
    public enum Type {
        Checking, Savings, Loan
    }

    private Type type;
    private String id;
    private String name;
    private double balance;

    public Account(Type type, String id, String name, double balance) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account(Account source) {
        this.type = source.type;
        this.id = source.id;
        this.name = source.name;
        this.balance = source.balance;
    }

    public abstract Account clone();

    public abstract boolean withdraw(double amount);

    public abstract void deposit(double amount);

    protected double round(double amount) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(amount));
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        //Added if statements due to output format issues with short names and Loan accounts.
        if (getName().length() <= 11 && getName().length() > 9) {
            if (getType() == Type.Loan) {
                return getType() + "" +
                        "\t\t\t\t" + name + "      " +
                        "\t" + id + "    " +
                        "\t\t\t$" + balance + "";
            }

            return getType() + "   " +
                    "\t\t\t" + name + "      " +
                    "\t" + id + "    " +
                    "\t\t\t$" + balance + "";
        }

        else if (getName().length() <= 9) {
            if (getType() == Type.Loan) {
                return getType() + "" +
                        "\t\t\t\t" + name + "       " +
                        "\t\t" + id + "    " +
                        "\t\t\t$" + balance + "";
            }

            return getType() + "   " +
                    "\t\t\t" + name + "       " +
                    "\t\t" + id + "    " +
                    "\t\t\t$" + balance + "";
        }

        else {
            if(getType() == Type.Loan) {
            return getType() + "" +
                    "\t\t\t\t" + name + "    " +
                    "\t" + id + "     " +
                    "\t\t\t$" + balance + "";
            }

            return getType() + "   " +
                    "\t\t\t" + name + "    " +
                    "\t" + id + "     " +
                    "\t\t\t$" + balance + "";
            }

    }
}
