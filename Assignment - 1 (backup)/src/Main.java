public class Main {
    public static void main(String[] args) {

        Account sourabh = new SavingsAccount("Sourabh",40000.0);
        Account aman = new CurrentAccount("Aman",2000.0);

        LoanService loanService = new LoanService();

        // Transactions
        sourabh.withdraw(1000);
        aman.withdraw(2000);

        sourabh.getBalance();
        aman.getBalance();

        ((SavingsAccount) sourabh).interestCalculation(5);

        // Loan services
        loanService.applyHomeLoan(sourabh,1000000.0);
        loanService.applyAutoLoan(aman, 50000.0);
    }
}
