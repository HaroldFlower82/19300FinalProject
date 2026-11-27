import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/* Main class for the Expense Manager application. This is where the program starts.
* @author Gabriel Rodriguez
* @version 27-Nov-26
*/
public class Main {
    private static final String DATA_CSV = "expenses.csv";
    private static final ExpenseManager EXPENSE_MANAGER = new ExpenseManager();
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
                addExpense();
            } else if (choice.equals("2")) {
                viewExpenses();
            } else if (choice.equals("3")) {
                deleteExpense();
            } else if (choice.equals("4")) {
                editExpense();
            } else if (choice.equals("5")) {
                viewFullTotal();
            } else if (choice.equals("6")) {
                viewCategoricalTotal();
            } else if (choice.equals("7")) {
                break;
            } else {
                IO.println("\nPlease enter a number from 1 - 7.");
            }
        }

        IO.println("\nThank you for using the Personal Expense Tracker!");
    }
    
    //Menu method
    private static void printMenu() {
        IO.println("\nMENU:");
        IO.println("1. Add expense");
        IO.println("2. View expenses");
        IO.println("3. Delete expense");
        IO.println("4. Edit expense");
        IO.println("5. View total");
        IO.println("6. View totals by category");
        IO.println("7. Exit");
    }

    //Adds an expense to the ledger
    private static void addExpense() {
        IO.println("\n=== Add Expense ===");
        String name = promptGeneral("Name: ");
        String description = promptGeneral("Description: ");
        double amount = promptDouble("Amount: ");
        String category = promptGeneral("Category: ");
        LocalDate date = promptDate("Date (YYYY-MM-DD): ", LocalDate.now());

        EXPENSE_MANAGER.addExpense(new Expense(name, description, amount, category, date));
        IO.println("Expense added successfully!");
    }

    //Views all expenses currently in the ledger
    private static void viewExpenses() {
        IO.println("\n=== Current Expenses ===");
        if (EXPENSE_MANAGER.size() == 0) {
            IO.println("No expenses recorded.");
            return;
        }
        int i = 1;
        for (Expense e : EXPENSE_MANAGER.getAllExpenses()) {
            IO.println(String.format("%d. %s", i, e.toString()));
            i++;
        }
    }

    //Delete an expense
    private static void deleteExpense() {

    }
    //Edit an expense
    private static void editExpense() {

    }
    
    //View total of all expenses
    private static void viewFullTotal() {

    }

    //View total of expenses in a category
    private static void viewCategoricalTotal() {

    }

    //Prompts for user input facilitation
    private static String promptGeneral(String prompt) {
        while (true) {
            String txt = IO.readln(prompt);
            if (txt == null || txt.isEmpty()) {
                return "Empty";
            } else {
                return txt;
            }
        }
    }

    private static double promptDouble(String prompt) {
        while (true) {
            String txt = IO.readln(prompt);
            if (txt == null) {
                return 0.0;
            }
            txt = txt.replace("$", "").replace(",", "");
            try {
                double amount = Double.parseDouble(txt);
                if (amount >= 0) {
                    return amount;
                } else {
                    IO.println("Amount cannot be negative. Please try again.");
                }
            } catch (NumberFormatException e) {
                IO.println("Invalid number. Please try again.");
            }
        }
    }

    private static LocalDate promptDate(String prompt, LocalDate defaultDate) {
        while (true) {
            String txt = IO.readln(prompt);
            if (txt == null || txt.isEmpty()) {
                return defaultDate;
            }
            try {
                return LocalDate.parse(txt);
            } catch (DateTimeParseException e) {
                IO.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
}