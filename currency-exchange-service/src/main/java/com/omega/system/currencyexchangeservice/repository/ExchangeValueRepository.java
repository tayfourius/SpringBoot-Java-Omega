package com.omega.system.currencyexchangeservice.repository;

import com.omega.system.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
    ExchangeValue findByFromAndTo(String from, String to);
}
