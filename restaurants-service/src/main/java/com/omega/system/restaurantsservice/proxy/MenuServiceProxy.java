package com.omega.system.restaurantsservice.proxy;

import com.omega.system.restaurantsservice.model.MenuModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="menu-service")
@RibbonClient(name="menu-service")
//@FeignClient(name="gateway-service")
public interface MenuServiceProxy {


    @GetMapping("menu-service/restaurant/{id}/menu/")
    //@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public Iterable<MenuModel> retrieveMenu
            (@PathVariable("id") long id);


}
