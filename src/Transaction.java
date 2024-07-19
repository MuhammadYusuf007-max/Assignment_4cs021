public class Transaction {
    public void transfer(Account source, Account destination, int amount) {
        if (source.getBalance() >= amount) {
            source.withdraw(amount);
            destination.deposit(amount);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}

