import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/* Class for the expense manager, which manages a list of expenses and provides methods to manipulate them.
* @author Gabriel Rodriguez
* @version 27-Nov-26
*/
public class ExpenseManager {
    private static String HEADER = "name,description,amount,category,date";
    private static ArrayList<Expense> expenses = new ArrayList<>();

    //MUTATOR METHODS
    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static boolean deleteExpense(int number) {
        if (number < 1 || number > expenses.size()) {
            return false;
        }
        expenses.remove(number - 1);
        return true;
    }

    //ACCESSOR METHODS
    public static ArrayList<Expense> getAllExpenses() {
        return expenses;
    }

    public static int size() {
        return expenses.size();
    }

    public static Expense getExpense(int number) {
        if (number < 1 || number > expenses.size()) {
            return null;
        }
        return expenses.get(number - 1);
    }

    public static double calculateTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public static double calculateCategoryTotal(String category) {
        double total = 0;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                total += e.getAmount();
            }
        }
        return total;
    }

    //CSV Management
    public static void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(HEADER);
            for (Expense e : expenses) {
                pw.println(e.convertToCSV());
            }
        }
    }

    public static int loadFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            return 0;
        }
        int skipped = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    Expense expense = Expense.convertFromCSV(line);
                    expenses.add(expense);
                } catch (Exception e) {
                    skipped++;
                }
            }
        }
        return skipped;
    }
}