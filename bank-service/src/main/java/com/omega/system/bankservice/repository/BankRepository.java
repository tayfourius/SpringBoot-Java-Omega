package com.omega.system.bankservice.repository;

import com.omega.system.bankservice.model.BankModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankRepository extends CrudRepository<BankModel, Integer> {
    BankModel findByAccount(String account);
}
