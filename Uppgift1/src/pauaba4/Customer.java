package pauaba4; 
import java.util.ArrayList;
import java.util.List;

/**
 @author Paulina Abarca jara 
 @author pauaba4 
 */
public class Customer { //klass namn customer
    
    private String firstName; //kundens namn
    private String lastName; //efternamn
    private String personalNumber; //kundens personnr
    private List<Account> accounts; //en lista med kundens konto
    

    //En ny kund ska skapas med namn,efternan och personnr
    public Customer(String firstName, String lastName, String personalNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.accounts = new ArrayList<>();  // Skapa en tom lista för konton
    }
   
    public String getFirstName() {  //hämtar kundens namn
        return firstName; //den returneras som svar.
    }
   
    public String getLastName() { // samma sak med efternamn
        return lastName;
    }
    
    public String getPersonalNumber() {
        return personalNumber;
    }
    
    public List<Account> getAccounts() { // Vi ska hämta kundens konto från listan
        return accounts;
    }
    
    /*ÄNDRING AV NAMN*/
    public void setFirstName(String firstName) { // nu sätter vi att vi kan ändra kundens namn
        // och OM strängen inte är tom, ska namnet uppdateras
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName;// Om strängen är tom, så ska inget göras( och de gamla namnet behålls)
        }
        
    }
 // samma sak här fast för efternamnet.
    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
    }

    public void addAccount(Account account) { // ett nytt konto ska skapas till kund 
        accounts.add(account); 
    }
    
    //Vi ska fetcha ett konto med hjälp av konto id
    public Account getAccount(int accountId) {
        // och ha en for loop för att kolla igenom alla konton
        for (Account account : accounts) {
            // ett if loop ska jämför kontonummer
            if (account.getAccountNumber() == accountId) {
                return account;  // ett retur om konto hhittades
            }
        }
        return null;  // Kontot finns inte hos denna kund
    }
    
    public boolean removeAccount(Account account) { //Vi specifiserar att ett konto ska raderas
        return accounts.remove(account); //true om kontot togs bort
    }
  
    // Hämta kund info 
    public String getCustomerInfo() {  //samt vad vi ska hämta.
        return personalNumber + " " + firstName + " " + lastName;
    }
    
    //Vi skapar en lista som ska ha all kundinfo samt konto
    public List<String> getCustomerInfoWithAccounts() {
        List<String> info = new ArrayList<>(); 
        
        // Lägger till kundinfo som första element
        info.add(getCustomerInfo());
        
        // En for loop som lägg till information för varje konto
        for (Account account : accounts) {
            info.add(account.getAccountInfo());
        }
        
        return info;
    }
   
    //En ny lista som ska hämta kundinfo med konto och ränta.
    public List<String> getCustomerInfoWithAccountsAndInterest() {
        List<String> info = new ArrayList<>();
        
        // samma som innan.
        info.add(getCustomerInfo());
        
        // Lägg till information för varje konto med ränta
        for (Account account : accounts) {
            info.add(account.getAccountInfoWithInterest());
        }
        
        return info;
    }
}