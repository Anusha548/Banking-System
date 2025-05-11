package com.tns.banking.application;

import java.util.*;

import com.tns.banking.entities.Account;
import com.tns.banking.entities.Beneficiary;
import com.tns.banking.entities.Customer;
import com.tns.banking.entities.Transaction;
import com.tns.banking.services.BankingService;
import com.tns.banking.services.BankingServiceImpl;

public class BankingSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingService bankingService = new BankingServiceImpl();

        while (true) {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. View Customer Accounts");
            System.out.println("6. View Account Transactions");
            System.out.println("7. Add Beneficiary");
            System.out.println("8. View Beneficiaries");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();
                    Customer customer = new Customer(cid, name, address, contact);
                    bankingService.addCustomer(customer);
                    System.out.println("Customer added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Account ID: ");
                    int aid = sc.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int accCustomerID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Account Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    Account account = new Account(aid, accCustomerID, type, balance);
                    bankingService.addAccount(account);
                    System.out.println("Account created successfully.");
                    break;

                case 3:
                    System.out.print("Enter Transaction ID: ");
                    int tidDeposit = sc.nextInt();
                    System.out.print("Enter Account ID: ");
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = sc.nextDouble();
                    Transaction deposit = new Transaction(tidDeposit, "deposit", depositAmount);
                    bankingService.addTransaction(deposit);
                    System.out.println("Deposit successful.");
                    break;

                case 4:
                    System.out.print("Enter Transaction ID: ");
                    int tidWithdraw = sc.nextInt();
                    System.out.print("Enter Account ID: ");
                    int accIdWithdraw = sc.nextInt();
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    Account acc = bankingService.findAccountById(accIdWithdraw);
                    if (acc != null && acc.getBalance() >= withdrawAmount) {
                        Transaction withdraw = new Transaction(tidWithdraw, "withdraw", withdrawAmount);
                        bankingService.addTransaction(withdraw);
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance or invalid account.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Customer ID: ");
                    int custId = sc.nextInt();
                    List<Account> accounts = bankingService.getAccountsByCustomerId(custId);
                    if (!accounts.isEmpty()) {
                        for (Account a : accounts) {
                            System.out.println(a);
                        }
                    } else {
                        System.out.println("No accounts found for this customer.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Account ID: ");
                    int accountId = sc.nextInt();
                    List<Transaction> txns = bankingService.getTransactionsByAccountId(accountId);
                    if (!txns.isEmpty()) {
                        for (Transaction t : txns) {
                            System.out.println(t);
                        }
                    } else {
                        System.out.println("No transactions found for this account.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Beneficiary ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int bCustId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Beneficiary Name: ");
                    String bName = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    String bAccNum = sc.nextLine();
                    System.out.print("Enter Bank Details: ");
                    String bBank = sc.nextLine();
                    Beneficiary beneficiary = new Beneficiary(bid, bCustId, bName, bAccNum, bBank);
                    bankingService.addBeneficiary(beneficiary);
                    System.out.println("Beneficiary added successfully.");
                    break;

                case 8:
                    System.out.print("Enter Customer ID: ");
                    int bcid = sc.nextInt();
                    List<Beneficiary> benList = bankingService.getBeneficiariesByCustomerId(bcid);
                    if (!benList.isEmpty()) {
                        for (Beneficiary b : benList) {
                            System.out.println(b);
                        }
                    } else {
                        System.out.println("No beneficiaries found for this customer.");
                    }
                    break;

                case 9:
                    System.out.println("Exiting Banking System. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
