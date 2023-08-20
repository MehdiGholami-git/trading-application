package com.db.trading.service;


import com.db.trading.model.entities.Signal;
import com.db.trading.repository.SignalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignalServiceImpl implements SignalService {
  @Autowired
  private SignalRepository signalRepository;

  @Override
  public List<Signal> findBySignalNo(Integer signalNo) {
    return signalRepository.findBySignalNo(signalNo);
  }
}