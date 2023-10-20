package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.Transaction;

public class CategoryFilter implements TransactionFilter {
    
    String category;
    
    public CategoryFilter(String category) {
        this.category = category;
    }
    
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

}