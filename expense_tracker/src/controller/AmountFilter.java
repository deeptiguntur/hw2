package controller;

import java.util.List;

import model.Transaction;
import java.util.stream.Collectors;

public class AmountFilter implements TransactionFilter {
    
    private double minAmount;
    private double maxAmount;
    
    public AmountFilter(double minAmount, double maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }
    
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getAmount() >= minAmount && transaction.getAmount() <= maxAmount).collect(Collectors.toList());
    }

}