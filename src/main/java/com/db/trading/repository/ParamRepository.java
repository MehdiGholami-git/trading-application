package com.db.trading.repository;

import com.db.trading.model.entities.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param, Long> {}
