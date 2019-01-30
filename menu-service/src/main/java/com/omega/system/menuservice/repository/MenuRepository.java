package com.omega.system.menuservice.repository;

import com.omega.system.menuservice.model.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuModel, Integer> {

    Iterable<MenuModel> findByRestaurantId(int id);
}
