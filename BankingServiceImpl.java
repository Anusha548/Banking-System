package com.tns.banking.services;

import java.util.*;

import com.tns.banking.entities.Account;
import com.tns.banking.entities.Beneficiary;
import com.tns.banking.entities.Customer;
import com.tns.banking.entities.Transaction;

public class BankingServiceImpl implements BankingService {
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, Transaction> transactions = new HashMap<>();
    private Map<Integer, Beneficiary> beneficiaries = new HashMap<>();

    @Override
    public void addCustomer1(Customer customer) {
        customers.put(customer.getCustomerID(), customer);
    }

    @Override
    public void addAccount1(Account account) {
        accounts.put(account.getAccountID(), account);
    }

    @Override
    public void addTransaction1(Transaction transaction) {
        transactions.put(transaction.getTransactionID(), transaction);
        Account account = accounts.get(transaction.getAccountID());
        if (account != null) {
            double balance = account.getBalance();
            if (transaction.getType().equalsIgnoreCase("deposit")) {
                account.setBalance(balance + transaction.getAmount());
            } else if (transaction.getType().equalsIgnoreCase("withdraw")) {
                account.setBalance(balance - transaction.getAmount());
            }
        }
    }

    @Override
    public void addBeneficiary1(Beneficiary beneficiary) {
        beneficiaries.put(beneficiary.getBeneficiaryID(), beneficiary);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accounts.get(id);
    }

    @Override
    public Transaction findTransactionById(int id) {
        return transactions.get(id);
    }

    @Override
    public Beneficiary findBeneficiaryById(int id) {
        return beneficiaries.get(id);
    }

    @Override
    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account acc : accounts.values()) {
            if (acc.getCustomerID() == customerId) {
                result.add(acc);
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction txn : transactions.values()) {
            if (txn.getAccountID() == accountId) {
                result.add(txn);
            }
        }
        return result;
    }

    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerId) {
        List<Beneficiary> result = new ArrayList<>();
        for (Beneficiary b : beneficiaries.values()) {
            if (b.getCustomerID() == customerId) {
                result.add(b);
            }
        }
        return result;
    }
    
    @override
    public Collection<Account> getAllAccounts() {
    	return accounts.values();
    }
    	
    @override
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
    
    @override
    public Collection<Transaction> getAllTransactions() {
        return transactions.values();
    }
    	
    @override
    public Collection<Beneficiary> getAllBeneficiaries() {
        return beneficiaries.values();
    }

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBeneficiary11(Beneficiary beneficiary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Beneficiary> getAllBeneficiary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBeneficiary(Beneficiary beneficiary) {
		// TODO Auto-generated method stub
		
	}
}


