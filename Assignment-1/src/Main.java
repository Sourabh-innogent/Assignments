

public class Main {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount((double)500.0F);
        CurrentAccount currentAccount = new CurrentAccount((double)500.0F);
        savingsAccount.withdraw((double)500.0F);
        currentAccount.withdraw((double)500.0F);
        System.out.println(savingsAccount.getBalance());
        System.out.println(currentAccount.getBalance());
        savingsAccount.applyHomeLoan((double)400000.0F);
        currentAccount.applyAutoLoan((double)250000.0F);
    }
}
