package com.db.trading.repository;

import com.db.trading.model.entities.AlgoMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoMethodRepository extends JpaRepository<AlgoMethod, Long> {
  AlgoMethod findByMethodName(String name);
}
