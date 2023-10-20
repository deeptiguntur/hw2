package controller;

import view.ExpenseTrackerView;

import java.util.List;


import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
  public void applyFilter(boolean amountFilter) {
    List<Transaction> transactions;
    if (amountFilter) {
      AmountFilter amtFilter = new AmountFilter(view.getMinAmtField(), view.getMaxAmtField());
      transactions = amtFilter.filter(model.getTransactions());
    } else {
      CategoryFilter categoryFilter = new CategoryFilter(view.getFilterCategoryField());
      transactions = categoryFilter.filter(model.getTransactions());
    }
    view.filterTransactions(transactions);
    view.refreshTable(model.getTransactions());
  }
}