package controller;

import java.util.List;

public class CategoryFilter implements TransactionFilter {
    
    private String category;
    
    public CategoryFilter(String category) {
        this.category = category;
    }
    
    public static List<Transaction> filter(List<Transaction> transactions) {
        return transactions.filter(transaction -> transaction.category == this.category);
    }

}