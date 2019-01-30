package com.omega.system.adminservice.proxy;

import com.omega.system.adminservice.model.BankModel;
import com.omega.system.adminservice.model.UserModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name="login-service")
@RibbonClient(name = "bank-service")
@FeignClient(name = "gateway-service")
public interface BankServiceProxy {


    @PostMapping("/bank-service/user/{username}/password/{password}")
    BankModel retrieveUser
            (@PathVariable("username") String username, @PathVariable("password") String password);


}
