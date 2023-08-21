package com.db.trading.service;

import com.db.trading.lib.Algo;
import com.db.trading.model.entities.Signal;
import com.db.trading.repository.SignalRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignalServiceImpl implements SignalService {
  public static final String DO_ALGO = "doAlgo";
  public static final String CANCEL_TRADES = "cancelTrades";
  public static final String REVERSE = "reverse";
  public static final String SUBMIT_TO_MARKET = "submitToMarket";
  public static final String PERFORM_CALC = "performCalc";
  public static final String SET_UP = "setUp";
  public static final String SET_ALGO_PARAM = "setAlgoParam";
  @Autowired
  private Algo algo;

  @Autowired
  private SignalRepository signalRepository;

  @Override
  public List<Signal> findBySignalNo(Integer signalNo) {
    return signalRepository.findBySignalNo(signalNo);
  }

  @Override
  public List<String> handleSignal(Integer signalNo) {
    List<Signal> signals = signalRepository.findBySignalNo(signalNo);
    List<String> methods = new ArrayList<>();
    for (Signal signal : signals) {
      callAlgo(signal);
      methods.add(signal.getAlgoMethod().getMethodName());
    }
    algo.doAlgo();
    return methods;
  }

  private void callAlgo(Signal signal) {
    switch (signal.getAlgoMethod().getMethodName()) {
      case DO_ALGO:
        algo.doAlgo();
        break;
      case CANCEL_TRADES:
        algo.cancelTrades();
        break;
      case REVERSE:
        algo.reverse();
        break;
      case SUBMIT_TO_MARKET:
        algo.submitToMarket();
        break;
      case PERFORM_CALC:
        algo.performCalc();
        break;
      case SET_UP:
        algo.setUp();
        break;
      case SET_ALGO_PARAM:
        algo.setAlgoParam(signal.getParam().getParamOne(), signal.getParam().getParamTwo());
        break;
      default:
        algo.cancelTrades();
        break;
    }
  }
}