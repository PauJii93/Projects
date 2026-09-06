package pauaba4; 
import java.text.NumberFormat;
import java.util.Locale;

/**

 @author Paulina Abarca jara 
 @author pauaba4 
 */
//KLass namn account
public class Account {
    private int accountNumber;  //Ett konto nummer för kunder
    private double balance; // decimaltal på summan
    private double interestRate; // Ränteinsats
    private String accountType; // specifika konto typen för kunden, så som sparkonto eller vanlig konto
    
public Account(int accountNumber) { //Nytt konto skapas med de angivna konto nr
        this.accountNumber = accountNumber;  // kommer spara kontonumret
        this.balance = 0;                     // alla nya konton börjar med 0
        this.interestRate = 2.4;              // självaste räntan
        this.accountType = "Sparkonto";       // all kontotyp är sparkonto
    }
    

    public int getAccountNumber() { //hämta ett konto med hjälp av konto numret.
        return accountNumber; // den returnerar kontonr
    }
    
    public double getBalance() { //saldot ska hämtas från kontot
        return balance; //den nuvarande saldo ska returneras
    }
    
    public double getInterestRate() {  //hämtar räntesatsen
        return interestRate;  //samt returnera den i procent
    }

    public String getAccountType() { //Ska hämta kontotypen från kunder
        return accountType; //ska returnera vilket typ av konto, vilket blir sparkont.
    }
    // deposit / sätter in pengar på kontot
    public boolean deposit(int amount) {
        if (amount > 0) {    //OM summan är mindre än noll
            balance += amount;  // så ska saldot öka med beloppet
            return true;         // om return är sann, så har insättningen lyckades
        }
        return false;            // men om returen är false, så är beloppet ej giltig
    }
  
    //Ett uttag från konto där summan måste vara högre än 0
    public boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount) { //är summan mer än 0
            balance -= amount;  // så minskas saldot med beloppet
            return true;         
        }
        return false;            // retur false om det är otillräckligt saldo eller ogiltigt belopp
    }
    public double calculateInterest() {  // de nuvarande saldo ska beräknas
        return balance * interestRate / 100; //vi ska få utan decimaler och i hela tal, därför gångra med 100
    }
    
    private String formatBalance() { //en format för att kunna få summan rätt, med rätt deciamal och space.
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")); // numberformat för svenska krnor
        return currencyFormat.format(balance); // returen bör då bli korrekt med decimaler och space.
    }
    
    
    private String formatInterestRate() { // samma process fast för räntan
        NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.of("SV", "SE"));
        percentFormat.setMaximumFractionDigits(1); // begränsning så att det blir max 1 decimal
        return percentFormat.format(interestRate / 100); // delas med 100 för att få det i procent form
    }
    
    private String formatAmount(double amount) {  //vi säger att ett belopp ska formateras enligt svensk format.
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")); 
        return currencyFormat.format(amount);
    }
  
    //En ny string för att hämta konto information som är kopplad med banklogic
    public String getAccountInfo() {
        return accountNumber + " " + formatBalance() + " " + accountType + " " + formatInterestRate(); // den ska returnera konto nr och konto typ formaterat.
    }
  
    public String getAccountInfoWithInterest() { // hämta konto info med ränta
        double interest = calculateInterest(); // Denna kommer då koppla sig till banklogic - radera kund kod.
        return accountNumber + " " + formatBalance() + " " + accountType + " " + formatAmount(interest);
    }
}