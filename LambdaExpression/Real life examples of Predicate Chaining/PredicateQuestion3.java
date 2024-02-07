// Certainly! Here's an advanced Java interview question:

// Question:
// You are given a list of transactions where each transaction contains information about a trade, including the trader's name, the year of the trade, the value of the trade, and the currency of the trade. Write a Java program to find the total sum of trade values for all transactions that occurred in the year 2021, where the currency of the trade is "USD".
import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;

class Transaction {
    private String traderName;
    private int year;
    private int value;
    private String currency;

    public Transaction(String traderName, int year, int value, String currency) {
        this.traderName = traderName;
        this.year = year;
        this.value = value;
        this.currency = currency;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "traderName='" + traderName + '\'' +
                ", year=" + year +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}

public class PredicateQuestion3 {
    public static void main(String[] args) {
        // Define all filters
        Predicate<Transaction> isYear2021 = transaction -> transaction.getYear() == 2021;
        Predicate<Transaction> isCurrencyUSD = transaction -> transaction.getCurrency().equals("USD");

        Predicate<Transaction> filter = isYear2021.and(isCurrencyUSD);

        List<Transaction> transactions = Arrays.asList(
                new Transaction("Alice", 2020, 1500, "USD"),
                new Transaction("Bob", 2021, 2000, "USD"),
                new Transaction("Charlie", 2021, 2500, "EUR"),
                new Transaction("David", 2021, 1800, "USD"),
                new Transaction("Eve", 2020, 3000, "USD"));

        long totalTrades = transactions.stream().filter(filter).mapToInt(Transaction::getValue).sum();
        System.out.println("Total Trades = " + totalTrades);

    }
}