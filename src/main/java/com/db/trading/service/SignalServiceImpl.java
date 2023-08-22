package com.db.trading.service;

import com.db.trading.exception.SignalNotFoundException;
import com.db.trading.lib.Algo;
import com.db.trading.model.entities.AlgoMethod;
import com.db.trading.model.entities.Param;
import com.db.trading.model.entities.Signal;
import com.db.trading.repository.AlgoMethodRepository;
import com.db.trading.repository.ParamRepository;
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

  @Autowired
  AlgoMethodRepository algoMethodRepository;

  @Autowired
  ParamRepository paramRepository;

  @Override
  public List<Signal> findBySignalNo(Integer signalNo) {
    return signalRepository.findBySignalNo(signalNo);
  }

  @Override
  public List<String> handleSignal(Integer signalNo) {
    init();// just to prepare data for DB(could be removed)
    List<Signal> signals = signalRepository.findBySignalNo(signalNo);
    if (signals.isEmpty()) {
      throw new SignalNotFoundException("signalNo: " + signalNo +" not found!");
    }
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
      case DO_ALGO -> algo.doAlgo();
      case CANCEL_TRADES -> algo.cancelTrades();
      case REVERSE -> algo.reverse();
      case SUBMIT_TO_MARKET -> algo.submitToMarket();
      case PERFORM_CALC -> algo.performCalc();
      case SET_UP -> algo.setUp();
      case SET_ALGO_PARAM -> algo.setAlgoParam(signal.getParam().getParamOne(), signal.getParam().getParamTwo());
      default -> algo.cancelTrades();
    }
  }

  public void init() {
    paramRepository.saveAll(List.of(Param.builder().id(1L).paramOne(1).paramTwo(60).build(),
        Param.builder().id(2L).paramOne(1).paramTwo(80).build(),
        Param.builder().id(3L).paramOne(1).paramTwo(90).build(),
        Param.builder().id(4L).paramOne(2).paramTwo(15).build()));
    algoMethodRepository.saveAll(List.of(AlgoMethod.builder().id(1L).methodName(DO_ALGO).build(),
        AlgoMethod.builder().id(2L).methodName(CANCEL_TRADES).build(),
        AlgoMethod.builder().id(3L).methodName(REVERSE).build(),
        AlgoMethod.builder().id(4L).methodName(SUBMIT_TO_MARKET).build(),
        AlgoMethod.builder().id(5L).methodName(PERFORM_CALC).build(),
        AlgoMethod.builder().id(6L).methodName(SET_UP).build(),
        AlgoMethod.builder().id(7L).methodName(SET_ALGO_PARAM).build()));
    List<Param> params = paramRepository.findAll();
    List<AlgoMethod> methods = algoMethodRepository.findAll();
    signalRepository.saveAll(List.of(Signal.builder().id(1L).signalNo(1).algoMethod(methods.get(5)).build(),
        Signal.builder().id(2L).signalNo(1).algoMethod(methods.get(6)).param(params.get(0)).build(),
        Signal.builder().id(3L).signalNo(1).algoMethod(methods.get(4)).build(),
        Signal.builder().id(4L).signalNo(1).algoMethod(methods.get(3)).build(),
        Signal.builder().id(5L).signalNo(2).algoMethod(methods.get(2)).build(),
        Signal.builder().id(6L).signalNo(2).algoMethod(methods.get(6)).param(params.get(1)).build(),
        Signal.builder().id(7L).signalNo(2).algoMethod(methods.get(3)).build(),
        Signal.builder().id(8L).signalNo(3).algoMethod(methods.get(6)).param(params.get(2)).build(),
        Signal.builder().id(9L).signalNo(3).algoMethod(methods.get(6)).param(params.get(3)).build(),
        Signal.builder().id(10L).signalNo(3).algoMethod(methods.get(4)).build(),
        Signal.builder().id(11L).signalNo(3).algoMethod(methods.get(3)).build()));
  }
}