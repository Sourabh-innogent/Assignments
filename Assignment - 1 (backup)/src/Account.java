
class Account {
    protected double balance;

    Account(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal of "+ amount +"successful. Remaining balance: ₹" + this.balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        System.out.print("Available Balance:");
        return this.balance;
    }
}
