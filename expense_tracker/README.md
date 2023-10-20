# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

# Description

# Add Transaction
For adding a transaction, there are two field Amount and Category and one Add Transaction controller button <br />
Amount - Enter Amount <br />
Category - Enter Category <br />
Add Transaction - Transaction will get added to transaction list <br />
<br />

# Filter
For filtering transactions, there are two ways to filter - Filter by Amount and Filter by Category <br />
<br />
Filter by Amount has 2 fields - Min and Max amount and a Filter by Amount controller button <br />
Min Amount - Enter Min Amount <br />
Max Amount - Enter Max Amount <br />
Filter by Amount - Transactions with amount in range between min amount and max amount inclusive will get highlighted in GREEN <br />
<br />
Filter by Category has 1 field - Category and a Filter by Category controller button<br />
Category - Enter Category <br />
Filter by Category - Transactions with this category will get highlighted in GREEN <br />