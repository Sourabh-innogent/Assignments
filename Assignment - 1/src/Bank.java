
// Interface Segregation Principle (ISP):
// Separate interfaces for different loan types so that classes only implement what they need.
interface HomeLoan {
    void applyHomeLoan(double amount);
}

interface AutoLoan {
    void applyAutoLoan(double amount);
}

// OOP: Encapsulation — balance is protected, accessible via methods only.
// OOP: Abstraction — general account behavior defined in base class.
// SOLID: SRP — Account class handles only account-related behavior (deposit/withdraw).
class Account {
    protected double balance;

    Account(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}

//  OOP: Inheritance — SavingsAccount inherits Account behavior.
//  SOLID: ISP — Implements only HomeLoan, not forced to implement AutoLoan.
//  SOLID: LSP — Can be used wherever Account is expected.
class SavingsAccount extends Account implements HomeLoan {
    SavingsAccount(double balance) {
        super(balance);
    }

    public void applyHomeLoan(double amount) {
        System.out.println("Home loan of ₹" + amount + " applied successfully for Savings Account.");
    }
}

//  OOP: Inheritance — CurrentAccount inherits from Account.
//  SOLID: ISP — Implements only AutoLoan, not forced to implement HomeLoan.
//  SOLID: LSP — Can be substituted in place of Account.
class CurrentAccount extends Account implements AutoLoan {
    public static final int MIN_BALANCE = 2000;

    CurrentAccount(double balance) {
        super(balance);
    }

    //  OOP: Polymorphism — Method overriding (custom withdraw logic).
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
        } else {
            System.out.println(" !!! Mandatory balance : " + MIN_BALANCE + " and remaining balance will be: " + (balance - amount));
        }
    }

    public void applyAutoLoan(double amount) {
        System.out.println("Auto loan of ₹" + amount + " applied successfully for Savings Account.");
    }
}

public class Bank {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount(500);
        CurrentAccount currentAccount = new CurrentAccount(500);

        savingsAccount.withdraw(500);
        currentAccount.withdraw(500);

        System.out.println(savingsAccount.getBalance());
        System.out.println(currentAccount.getBalance());

        //  OOP: Polymorphism through interfaces (HomeLoan, AutoLoan)
        savingsAccount.applyHomeLoan(400000);
        currentAccount.applyAutoLoan(250000);
    }
}
