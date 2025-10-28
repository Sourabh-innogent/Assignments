

public class Main {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount(40000.0F);
        CurrentAccount currentAccount = new CurrentAccount(2000.0F);

        //Transactions
        savingsAccount.withdraw(100.0F);
        currentAccount.withdraw(2000.0F);

        System.out.println(savingsAccount.getBalance());
        System.out.println(currentAccount.getBalance());

        currentAccount.withdraw(2000.0F);
        System.out.println(currentAccount.getBalance());

        savingsAccount.interestCalculation(5);

        savingsAccount.applyHomeLoan(100000.0F);
        currentAccount.applyAutoLoan(250000.0F);
    }
}
