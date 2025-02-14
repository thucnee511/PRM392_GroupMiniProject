package vn.edu.fpt.groupminiproject.models;

import java.io.Serializable;

public class Account implements Serializable {
    private String username;
    private String password;
    private String displayName;
    private int balance;

    public Account(String username, String password, String displayName, int balance) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getBalanceString() {
        //format balance to string with comma
        return String.format("%,d", balance);
    }
}
