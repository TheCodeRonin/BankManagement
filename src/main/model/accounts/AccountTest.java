package main.model.accounts;

import main.model.accounts.impl.Taxable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account[] accounts;

    @BeforeEach
    void setup() {
        accounts = new Account[] {
                new Checking("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51),
                new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60),
                new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", 2537.31)
        };
    }

    @Test
    void withdraw() {
        accounts[0].withdraw(1440);

        assertEquals(84.51, accounts[0].getBalance());
    }

    @Test
    void overdraft() {
        accounts[0].withdraw(1534.43);

        assertEquals(-15.42, accounts[0].getBalance());
    }

    @Test
    void overdraftLimit() {
        accounts[0].withdraw(1726);

        assertEquals(1524.51, accounts[0].getBalance());
    }

    @Test
    void withdrawalFee() {
        accounts[1].withdraw(100);

        assertEquals(2136.60, accounts[1].getBalance());
    }

    @Test
    void withdrawalInterest() {
        accounts[2].withdraw(2434.31);

        assertEquals(5020.31, accounts[2].getBalance());
    }

    @Test
    void withdrawalLimit() {
        accounts[2].withdraw(7463.69);

        assertEquals(2537.31, accounts[2].getBalance());
    }

    @Test
    void depositChecking() {
        accounts[0].deposit(5000);

        assertEquals(6524.51, accounts[0].getBalance());
    }

    @Test
    void depositSavings() {
        accounts[1].deposit(5000);

        assertEquals(7241.60, accounts[1].getBalance());
    }

    @Test
    void depositLoan() {
        accounts[2].deposit(1000);

        assertEquals(1537.31, accounts[2].getBalance());
    }

    @Test
    void incomeTax() {
        double income = 4000;
        accounts[0].deposit(income);

        ((Taxable) accounts[0]).tax(income);

        assertEquals(5374.51, accounts[0].getBalance());
    }
}