class SavingsAccount extends Account implements HomeLoan {
    SavingsAccount(double balance) {
        super(balance);
    }

    public void applyHomeLoan(double amount) {
        System.out.println("Home loan of ₹" + amount + " applied successfully for Savings Account.");
    }

    public void interestCalculation(int years) {
        double INTEREST_RATE = 0.04;
        double interest = this.balance * INTEREST_RATE * years;
        System.out.println("Interest for " + years + " year is: " + interest);
    }

}
