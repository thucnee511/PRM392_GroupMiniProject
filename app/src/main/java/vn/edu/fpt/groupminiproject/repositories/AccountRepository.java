package vn.edu.fpt.groupminiproject.repositories;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fpt.groupminiproject.models.Account;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();
    private static AccountRepository instance;
    private AccountRepository() {
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
        accounts.add(new Account("thuchnse171089", "12345678","Thucnee", 1000000));
    }

    public static AccountRepository getInstance(){
        if (instance == null) instance = new AccountRepository();
        return instance;
    }

    public Account checkLogin(String username, String password){
        if (username.isBlank() || username.isEmpty())
            return null;
        if (password.isBlank() || password.isEmpty())
            return null;
        for(Account acc : accounts){
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password))
                return acc;
        }
        return null;
    }
}
