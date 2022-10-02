package com.yoratyo.endOfDay.model;

public class Eod {
    private int id;
    private String name;
    private int age;
    private double balance;
    private double previous_balance;
    private double average_balance;
    private int free_transfer;

    public static String[] fields() {
        return new String[]{
                "id", "name", "age", "balance",
                "previous_balance", "average_balance", "free_transfer"
        };
    }

    public Eod(){}

    public Eod(int id, String name, int age, double balance, double previous_balance, double average_balance, int free_transfer) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.previous_balance = previous_balance;
        this.average_balance = average_balance;
        this.free_transfer = free_transfer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPrevious_balance() {
        return previous_balance;
    }

    public void setPrevious_balance(double previous_balance) {
        this.previous_balance = previous_balance;
    }

    public double getAverage_balance() {
        return average_balance;
    }

    public void setAverage_balance(double average_balance) {
        this.average_balance = average_balance;
    }

    public int getFree_transfer() {
        return free_transfer;
    }

    public void setFree_transfer(int free_transfer) {
        this.free_transfer = free_transfer;
    }
}