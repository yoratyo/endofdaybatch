package com.yoratyo.endOfDay.model;

public class EodResult {
    private int id;
    private String name;
    private int age;
    private double balance;
    private String thread_2b;
    private String thread_3;
    private double previous_balance;
    private double average_balance;
    private String thread_1;
    private int free_transfer;
    private String thread_2a;

    public EodResult(){}

    public EodResult(int id, String name, int age, double balance, String thread_2b, String thread_3, double previous_balance, double average_balance, String thread_1, int free_transfer, String thread_2a) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.thread_2b = thread_2b;
        this.thread_3 = thread_3;
        this.previous_balance = previous_balance;
        this.average_balance = average_balance;
        this.thread_1 = thread_1;
        this.free_transfer = free_transfer;
        this.thread_2a = thread_2a;
    }

    public static String[] fields() {
        return new String[]{
                "id", "name", "age", "balance", "thread_2b", "thread_3",
                "previous_balance", "average_balance", "thread_1", "free_transfer", "thread_2a"
        };
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

    public String getThread_2b() {
        return thread_2b;
    }

    public void setThread_2b(String thread_2b) {
        this.thread_2b = thread_2b;
    }

    public String getThread_3() {
        return thread_3;
    }

    public void setThread_3(String thread_3) {
        this.thread_3 = thread_3;
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

    public String getThread_1() {
        return thread_1;
    }

    public void setThread_1(String thread_1) {
        this.thread_1 = thread_1;
    }

    public int getFree_transfer() {
        return free_transfer;
    }

    public void setFree_transfer(int free_transfer) {
        this.free_transfer = free_transfer;
    }

    public String getThread_2a() {
        return thread_2a;
    }

    public void setThread_2a(String thread_2a) {
        this.thread_2a = thread_2a;
    }
}
