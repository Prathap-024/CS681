package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 implements BankAccount {
	
	private double balance = 0;
    private final ReentrantLock lock = new ReentrantLock();

    Condition sufficientFunds = lock.newCondition();
    Condition belowUpperLimitFunds = lock.newCondition();

    public void deposit(double amount) {
        lock.lock();
        try {
            System.out.println("Lock obtained");
            System.out.println("Current balance (d): " + balance);
            while (balance >= 300) {
                // Wait for the balance to go below 300
                try {
                    System.out.println(Thread.currentThread().getName() + " Balance limit exceeded ");
                    belowUpperLimitFunds.await();
                } catch (InterruptedException e) {
                    return;
                }
            }
            balance += amount;
            System.out.println(", New balance (d): " + balance);
            sufficientFunds.signalAll();
        } finally {
            lock.unlock();
            System.out.println("Lock has been released");
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            System.out.println("Lock has been obtained");
            System.out.println("Current balance (w): " + balance);

            while (balance <= 0) {
                // Wait for the balance to exceed 0
                try {
                    System.out.println(Thread.currentThread().getName() + " Balance is very low..");
                    sufficientFunds.await();
                } catch (InterruptedException e) {
                    return;
                }
            }
            balance -= amount;
            System.out.println(", New balance (w): " + balance);
            belowUpperLimitFunds.signalAll();
        } finally {
            lock.unlock();
            System.out.println("Lock has been released");
        }
    }

}
