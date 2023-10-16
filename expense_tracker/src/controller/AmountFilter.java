package controller;

import java.util.List;

public class AmountFilter implements TransactionFilter {
    
    private double minAmount;
    private double maxAmount;
    
    public CategoryFilter(double minAmount, double maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }
    
    public static List<Transaction> filter(List<Transaction> transactions) {
        return transactions.filter(transaction -> transaction.amount >= this.minAmount && transaction.amount <= this.maxAmount);
    }

}