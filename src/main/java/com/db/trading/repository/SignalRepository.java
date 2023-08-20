package com.db.trading.repository;

import com.db.trading.model.entities.Signal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignalRepository extends JpaRepository<Signal, Long> {
  List<Signal> findBySignalNo(Integer signalNo);
}