package com.db.trading.service;

import com.db.trading.model.entities.Signal;
import java.util.List;

public interface SignalService {
  List<Signal> findBySignalNo(Integer signalNo);

  List<String> handleSignal(Integer signalNo);
}