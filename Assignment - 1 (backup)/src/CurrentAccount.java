
class CurrentAccount extends Account{
    public static final double OVERDRAFT_LIMIT = 10000.0;

    CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        double availableFunds = this.balance + OVERDRAFT_LIMIT;

        if (amount <= availableFunds) {
            this.balance -= amount;
            if (this.balance < 0) {
                System.out.println(" You are using overdraft facility. Current overdraft: ₹" + (-this.balance));
            } else {
                System.out.println("Withdrawal of "+ amount +"successful. Remaining balance: ₹" + this.balance);
            }
        } else {
            System.out.println(" Withdrawal denied! Exceeds overdraft limit of ₹" + OVERDRAFT_LIMIT);
        }
    }

}
