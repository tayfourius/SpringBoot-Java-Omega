package com.omega.system.bankservice.controller;


import com.omega.system.bankservice.model.BankModel;
import com.omega.system.bankservice.model.UserModel;
import com.omega.system.bankservice.proxy.LoginServiceProxy;
import com.omega.system.bankservice.repository.BankRepository;
import com.omega.system.bankservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BankRepository bankRepository;

    @Autowired
    LoginServiceProxy loginServiceProxy;


    @Autowired
    TransactionService transactionService;

    @GetMapping("bank")
    public @ResponseBody
    Iterable<BankModel> getAllBank(
            @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        if (userModel == null) {
            return null;
        } else {
            if (userModel.getRole().equals("admin")) {
                return bankRepository.findAll();
            } else {
                return null;
            }
        }
    }

    @PostMapping("bank")
    public @ResponseBody
    String addNewAccount(@RequestBody BankModel restaurantsModel, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        logger.info("{}", userModel);
        if (userModel == null) {
            return "not user";
        } else {
            if (userModel.getRole().equals("admin")) {
                bankRepository.save(restaurantsModel);
                return "Saved";
            } else {
                return "not admin user";
            }
        }
    }

    @GetMapping("bank/{account}")
    public @ResponseBody
    BankModel getRestaurant(@PathVariable("account") String account, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel user = loginServiceProxy.retrieveUser(username, password);
        if (user == null) {
            return null;
        } else {
            BankModel bankModel = bankRepository.findByAccount(account);
            if (bankModel == null) {
                return null;
            } else {
                return bankModel;
            }
        }
    }

    @GetMapping("bank/{account}/price/{price}")
    public String checkAmmount(@PathVariable("account") String account, @PathVariable("price") int price, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel user = loginServiceProxy.retrieveUser(username, password);
        if (user == null) {
            return null;
        } else {
            BankModel bankModel = bankRepository.findByAccount(account);
            if (bankModel == null) {
                return null;
            } else {
                if (bankModel.getAmmout() >= price) {
                    return "Yes";
                } else {
                    return "No";
                }
            }
        }
    }

    @GetMapping("bank/{account}/restaurant/{restaurant}/amount/{amount}")
    public String Transaction(@PathVariable("account") String account, @PathVariable("restaurant") String restaurant, @PathVariable("amount") int amount, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel user = loginServiceProxy.retrieveUser(username, password);
        if (user == null) {
            return null;
        } else {
            BankModel userAccount = bankRepository.findByAccount(account);
            BankModel restaurantAccount = bankRepository.findByAccount(restaurant);
            if ((userAccount != null) && (restaurantAccount != null)) {
                transactionService.createTransaction(userAccount, restaurantAccount, amount);
                return "saved";
            } else {
                return null;
            }
        }
    }

}
