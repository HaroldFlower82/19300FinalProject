import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/* Main class for the Expense Manager application. This is where the program starts.
* @author Gabriel Rodriguez
* @version 23-Nov-26
*/
public class Main {
    private static final String DATA_CSV = "expenses.csv";
    //private static final ExpenseManager = new ExpenseManager();
    public static void main(String[] args) {
        IO.println("=== Personal Expense Tracker ===");
        
        /*try {
        * Load expenses from CSV file (NOT IMPLEMENTED YET)
        * } catch (IOException e) {
        *    IO.println("Could not read " + DATA_CSV + ": " + e.getMessage());
        * }
        */

        //Menu loop
        while (true) {
            printMenu();
            String choice = IO.readln("Enter your choice: ");
            if (choice == null) {
                IO.println("\nPlease enter a number from 1 - 6.");
            }

            if (choice.equals("1")) {

            } else if (choice.equals("2")) {

            } else if (choice.equals("3")) {

            } else if (choice.equals("4")) {

            } else if (choice.equals("5")) {

            } else if (choice.equals("6")) {
                break;
            } else {
                IO.println("\nPlease enter a number from 1 - 6.");
            }
        }

        IO.println("\nThank you for using the Personal Expense Tracker!");
    }

    private static void printMenu() {
        IO.println("\nMENU:");
        IO.println("1. Add expense");
        IO.println("2. View expenses");
        IO.println("3. Delete expense");
        IO.println("4. View total");
        IO.println("5. View totals by category");
        IO.println("6. Exit");
    }
}