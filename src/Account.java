public class Account extends Customer {
    private int balance;
    private int accountNumber;

    public Account(String firstName, String lastName, int accountNumber, int balance) {
        setFirstName(firstName);
        setLastName(lastName);
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
