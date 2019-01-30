package com.omega.system.bankservice.service;


import com.omega.system.bankservice.model.BankModel;
import com.omega.system.bankservice.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    BankRepository bankRepository;


    public void createTransaction(BankModel userAccount, BankModel restaurantAccount, int amount) {
        int removeAmount = userAccount.getAmmout() - amount;
        userAccount.setAmmout(removeAmount);
        bankRepository.save(userAccount);
        int addAmount = restaurantAccount.getAmmout() + amount;
        restaurantAccount.setAmmout(addAmount);
        bankRepository.save(restaurantAccount);
    }
}
