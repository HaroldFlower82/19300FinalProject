import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

/* Class for a single expense in the ledger
* @author Gabriel Rodriguez
* @version 22-Nov-26
*/
public class Expense {
    private NumberFormat GLOBAL_CURR = NumberFormat.getCurrencyInstance(Locale.US);
    private String name;
    private String description;
    private double amount;
    private String category;
    private LocalDate date;

    //CONSTRUCTOR
    public Expense(String name, String description, double amount, String category, LocalDate date) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    //ACCESSOR METHODS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    //MUTATOR METHODS
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //Get correct formatting for given currency
    public String formatCurrency(double value) {
        return GLOBAL_CURR.format(value);
    }

    //To string overload
    @Override
    public String toString() {
        return String.format("%s | %-20s | %-10s | %10s | %s",
                date, name, category, formatCurrency(amount), description);
    }

    //Datastore CSV Methods
    private String escapeCSV(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }
    public String convertToCSV() {
        return escapeCSV(name) + "," + escapeCSV(description) + "," + amount + "," + escapeCSV(category) + "," + date;
    }

    //CSV to String methods
    private List<String> splitCSV(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result;
    }
    public Expense convertFromCSV(String line) {
        List<String> fields = splitCSV(line);
        if (fields.size() != 5) {
            throw new IllegalArgumentException("Expected 5 fields but found " + fields.size());
        }
        try {
            return new Expense(
                    fields.get(0),
                    fields.get(1),
                    Double.parseDouble(fields.get(2)),
                    fields.get(3),
                    LocalDate.parse(fields.get(4)));
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Bad amount or date in line: " + line);
        }
    }
}