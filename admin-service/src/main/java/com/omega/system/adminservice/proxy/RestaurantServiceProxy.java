package com.omega.system.adminservice.proxy;

import com.omega.system.adminservice.model.RestaurantsModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name="login-service")
@RibbonClient(name = "restaurants-service")
@FeignClient(name = "gateway-service")
public interface RestaurantServiceProxy {


    @GetMapping("/restaurants-service/restaurant")
    RestaurantsModel retrieveUser
            (@RequestBody RestaurantsModel restaurantsModel, @RequestHeader("username") String username, @RequestHeader("password") String password);


}
