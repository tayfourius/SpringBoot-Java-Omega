package com.omega.system.bankservice.proxy;

import com.omega.system.bankservice.model.UserModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="login-service")
@RibbonClient(name = "login-service")
@FeignClient(name = "gateway-service")
public interface LoginServiceProxy {


    @GetMapping("/login-service/user/{username}/password/{password}")
    UserModel retrieveUser
            (@PathVariable("username") String username, @PathVariable("password") String password);


}
