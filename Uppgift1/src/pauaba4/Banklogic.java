package pauaba4; 

import java.util.ArrayList;
import java.util.List;

/**
@author Paulina Abarca jara 
@author pauaba4 
*/
public class Banklogic {
     // Klassnamn Banklogic
     
    private List<Customer> customers;
    
   //en lista ska skapas för mappen costumers där costumers sitter.
    
 private int nextAccountNumber;
    
    public Banklogic() {
        this.customers = new ArrayList<>();
        this.nextAccountNumber = 1001;  //detta är konto numret vi ska börja med
        // En ny lista(tom lista) skapas för kunder där vi bestämmer första konto numret.
    }
    
    private Customer findCustomer(String pNo) {  //filen costumers, vi sätter en findcostumers string som ska hitta person numbret vi ber om
        for (Customer customer : customers) {   // en for loop ska skapas för att berätta vad som ska hämta från vart. Costumer filen, hitta costumer - från costumer lista
            if (customer.getPersonalNumber().equals(pNo)) {  // en if loop skapas för att berätta vad som ska hända OM vi hittar. från (find)customer, där vi ska hämta/get personnr 
                return customer;  // retur om vi hittar customer.
            }
        }
        return null;  // Men om den ej hittas, så ska det komma upp som null/false.
    }
   
    private boolean customerExists(String pNo) {  // här ska vi försäkra oss om kunden redan finns eller ej. 
        return findCustomer(pNo) != null;  //finns kunden så ska personnr info hämtas/returneras som true, annars null/false
    }
   
    public List<String> getAllCustomers() {  // listan ska hämtas med alla kunder, en ny string.
        List<String> customerList = new ArrayList<>();  
        
        
        for (Customer customer : customers) { // FÖr denna customer fil innanför customer listan så ska den gå igenom alla kunder och lägg till deras information i listan
            customerList.add(customer.getCustomerInfo());
        }
        
        return customerList; // returnera listan
    }
  
    public boolean createCustomer(String name, String surname, String pNo) {  // Här specifiserar vi att en ny kund ska skapas med följande info. 
        // En if loop skapas för att kontrollera om kunden redan finns
        if (customerExists(pNo)) {
            return false;  // Och om de redan finns o personnumret finns redan, så ska inte kund skapas
        }
        
        // En ny kund skapas med en ny string där de ska ha följande information som ska hämtas.
        Customer newCustomer = new Customer(name, surname, pNo);
        
        // Vi specifiserar att vi vill lägg till kunden i listan
        customers.add(newCustomer);
        
        return true;  // och när de returneras så ska det komma som true.
    }
    
    
    public List<String> getCustomer(String pNo) { // Hitta kunden och deras konto/person nr.
        
        Customer customer = findCustomer(pNo);
        
        // Om kunden inte finns, returnera det som null
        if (customer == null) {
            return null;
        }
        
        
        return customer.getCustomerInfoWithAccounts(); // Returnera kundinformation med konton om det finns.
    }
   
    public boolean changeCustomerName(String name, String surname, String pNo) {  //skapar en string för kund informationen att kunnas ändras.
        // Hitta kunden genom personnr
        Customer customer = findCustomer(pNo);
        
        // IF loop om kunden inte finns och returneras som false.
        if (customer == null) {
            return false;
        }
        
        // Vi måste också fördyliga vad som kan ändras hos kunden.
        // Customer-klassen ska kontrollera om strängaren är tom
        customer.setFirstName(name);
        customer.setLastName(surname);
        
        return true;
    }
    
  
    //En kund ska tas bort och all dess konto info.
    public List<String> deleteCustomer(String pNo) {   
        // kunden ska hittas 
        Customer customer = findCustomer(pNo);
        
        // Men Om kunden inte finns så ska det returneras som null
        if (customer == null) {
            return null;
        }
        
        // Samla information om kunden och alla konton (med inklusive räntan)
        List<String> customerInfo = customer.getCustomerInfoWithAccountsAndInterest();
        
        // Sedan ska kunden tas bort från listan
        customers.remove(customer);
        
        // och returnera informationen
        return customerInfo;
    }
    
   
    public int createSavingsAccount(String pNo) {  //Nu skapar vi en ny int där ett sparkonto kommer skapas för kunder
        // Den ska hitta kunden, med personnr
        Customer customer = findCustomer(pNo);
        
        // men Om kunden inte finns, så ska den returnera -1. 
        if (customer == null) {
            return -1;
     }
        
        // Ett nytt konto ska skapas med nästa kontonummer
        Account newAccount = new Account(nextAccountNumber);
        
        // vi förtydligar att vi ska lägg till kontot till kunden
        customer.addAccount(newAccount);
        
        // och spara kontonumret för att returnera det
        int accountNumber = nextAccountNumber;
        
        // vi ska däremot också öka kontonummerräknaren för nästa konto, så att det blir en ordning
        nextAccountNumber++;
        
        
        return accountNumber;// Returnera det nya kontonumret
    }
    
    
    public String getAccount(String pNo, int accountId) { // samma sak som innan, vi ska hitta kund, fast nu med personnr och kundid
        
        Customer customer = findCustomer(pNo);
        
        // Om kunden inte finns returnera med null.
        if (customer == null) {
            return null;
        }
        
        // Hitta kontot genom kund id/konto id (kontonr,saldo,kontotyp och ränte)
        Account account = customer.getAccount(accountId);
        
        // Om kontot inte finns, returnera med null, false.
        if (account == null) {
            return null;
        }
        
        // Returnera kontoinformation
        return account.getAccountInfo();
    }
    
 
    //Här specifiserar vi att sättning av pengar på ett konto kommer ske.
    public boolean deposit(String pNo, int accountId, int amount) { // deposit/ insättning 
        // Hitta kunden
        Customer customer = findCustomer(pNo);
        
        // Om kunden inte finns
        if (customer == null) {
            return false;
        }
        
        // Hitta kontot genom id
        Account account = customer.getAccount(accountId);
        
        // Om kontot inte finns
        if (account == null) {
            return false;
        }
        
        // Försök sätta in pengarna
        // Account klassen ska då identifiera och kontrollerar om beloppet är giltigt
        return account.deposit(amount);
    }
    
    
    public boolean withdraw(String pNo, int accountId, int amount) {
        // Withdraw, utdrag av pengar med hjälp av id, personnr och summa.
        Customer customer = findCustomer(pNo);  //hitta kund
        
        // Om kunden inte finns
        if (customer == null) {
            return false;
        }
        
        // Hitta kontot med id
        Account account = customer.getAccount(accountId);
        
        // Om kontot inte finns
        if (account == null) {
            return false;
        }
        
       
        // Accountklassen ska kontrollerar om saldot räcker
        return account.withdraw(amount); // Försök ta ut pengarna
    }
    
   
    public String closeAccount(String pNo, int accountId) {
        // Hitta kunden
        Customer customer = findCustomer(pNo);
        
        // Om kunden inte finns
        if (customer == null) {
            return null;
        }
        
        // Hitta kontot
        Account account = customer.getAccount(accountId);
        
        // Om kontot inte finns
        if (account == null) {
            return null;
        }
        
        // konto info ska hämta kontoinformation med ränta
        String accountInfo = account.getAccountInfoWithInterest();
        
        // Tar bort kontot 
        customer.removeAccount(account);
        
        // Returnera informationen
        return accountInfo;
    }
}