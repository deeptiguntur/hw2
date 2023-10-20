package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.ExpenseTrackerModel;
import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JTextField minAmountField;
  private JTextField maxAmountField;
  private JTextField filterCategoryField;
  private JButton filterByAmount;
  private JButton filterByCategory;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"Serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    filterByAmount = new JButton("Filter By Amount");
    filterByCategory = new JButton("Filter By Category");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    JLabel filterSectionLabel = new JLabel("Filter");

    JLabel minAmountLabel = new JLabel("Min Amount:");
    minAmountField = new JTextField(10);

    JLabel maxAmountLabel = new JLabel("Max Amount:");
    maxAmountField = new JTextField(10);

    JLabel categoryFilterLabel = new JLabel("Category:");
    filterCategoryField = new JTextField(10);

    JPanel filterFieldPanel = new JPanel();
    filterFieldPanel.add(minAmountLabel);
    filterFieldPanel.add(minAmountField);
    filterFieldPanel.add(maxAmountLabel);
    filterFieldPanel.add(maxAmountField);
    filterFieldPanel.add(categoryFilterLabel);
    filterFieldPanel.add(filterCategoryField);

    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
    filterPanel.add(filterSectionLabel);
    filterPanel.add(filterFieldPanel);

    JPanel inputFilterPanel = new JPanel();
    inputFilterPanel.setLayout(new BoxLayout(inputFilterPanel, BoxLayout.Y_AXIS));
    inputFilterPanel.add(inputPanel);
    inputFilterPanel.add(filterPanel);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(filterByAmount);
    buttonPanel.add(filterByCategory);
  
    // Add panels to frame
    add(inputFilterPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  

  public void filterTransactions(List<Transaction> transactions) {
	  
	  getTransactionsTable().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	       @Override
	       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                                                     boolean hasFocus, int row, int column) {
	    	   int filteredRow = -1;
	    	   String CategoryValue = (String)table.getValueAt(row, 2);
	    	   double AmountValue = -1;
	    	   if (table.getValueAt(row, 1) != null) {	   
	    		   AmountValue = (double) table.getValueAt(row, 1);
	    	   }
	           for (Transaction t: transactions) {
	        	   if (AmountValue > 0 && AmountValue == t.getAmount() && CategoryValue != null && t.getCategory().equalsIgnoreCase(CategoryValue)) {
	        		   filteredRow = row;
	        	   }
	           }
	           Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, filteredRow, column);
	           if (filteredRow == row) {
	               c.setBackground(new Color(173, 255, 168)); // Light green
	           } else {
	               c.setBackground(table.getBackground());
	           }
	    	   return c;
	       }
	   });
  }

  public JButton filterByAmountBtn() {
    return filterByAmount;
  }

  public JButton filterByCategoryBtn() {
    return filterByCategory;
  }

  public double getMinAmtField() {
	  if (minAmountField.getText().isEmpty()) {
	      return 0;
	  } else {
		  double amount = 0;
		  try {
			  amount = Double.parseDouble(minAmountField.getText());
		  } catch(NumberFormatException error) {
			  return 0;
		  }
		  return amount;
	  }
  }

  public void setMinAmtField(JTextField minAmountField) {
    this.minAmountField = minAmountField;
  }

  public double getMaxAmtField() {
    if (maxAmountField.getText().isEmpty()) {
	      return 0;
	} else {
		double amount = 0;
		  try {
			  amount = Double.parseDouble(maxAmountField.getText());
		  } catch(NumberFormatException error) {
			  return 0;
		  }
		  return amount;
	}
  }

  public void setMaxAmtField(JTextField maxAmountField) {
    this.maxAmountField = maxAmountField;
  }

  public String getFilterCategoryField() {
    return filterCategoryField.getText();
  }

  public void setFilterCategoryField(JTextField filterCategoryField) {
    this.filterCategoryField = filterCategoryField;
  }
  

  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
