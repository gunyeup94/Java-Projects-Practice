/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    public int balance;
<<<<<<< HEAD
    public Account parentAccount;
=======
>>>>>>> 82d49a8f86de44bd346ef431816340221a8f46ff

    /** Initialize an account with the given BALANCE. */
    public Account(int balance) {
        this.balance = balance;
<<<<<<< HEAD
        this.parentAccount = null;
    }

    public Account(int balance, Account other){
        this.balance = balance;
        this.parentAccount = other;
    }



=======
    }

>>>>>>> 82d49a8f86de44bd346ef431816340221a8f46ff
    /** Deposits AMOUNT into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract AMOUNT from the account if possible. If subtracting AMOUNT
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
<<<<<<< HEAD
    public boolean withdraw(int amount) {
        // TODO
        int zero;
        int rest;
        if (amount < 0) {
            return false;
        } else if (this.balance < amount) {
            if (this.parentAccount != null){
                rest = amount - this.balance;
                if(rest < this.parentAccount.balance){
                    this.parentAccount.balance -= rest;
                    zero = amount - rest;
                    this.balance -= zero;
                    return true;
                } else{
                    return false;
                }
            } else {
                return false;
            }
        } else {
            this.balance -= amount;
            return true;
=======
    public void withdraw(int amount) {
        // TODO
        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
        } else if (balance < amount) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
>>>>>>> 82d49a8f86de44bd346ef431816340221a8f46ff
        }
    }

    /**
     * Merge account OTHER into this account by removing all money from OTHER
     * and depositing it into this account.
     */
    public void merge(Account other) {
<<<<<<< HEAD
        int x = other.balance;
        this.balance = balance + x;
        other.balance = other.balance - x;
=======
        // TODO
>>>>>>> 82d49a8f86de44bd346ef431816340221a8f46ff
    }
}
