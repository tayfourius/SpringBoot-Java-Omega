package com.omega.system.restaurantsservice.repository;

import com.omega.system.restaurantsservice.model.RestaurantsModel;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantsRepository extends CrudRepository<RestaurantsModel, Integer> {
}
