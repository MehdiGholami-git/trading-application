package com.db.trading.service;

import com.db.trading.model.entities.AlgoMethod;
import com.db.trading.model.entities.Param;
import com.db.trading.model.entities.Signal;
import com.db.trading.repository.AlgoMethodRepository;
import com.db.trading.repository.ParamRepository;
import com.db.trading.repository.SignalRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignalServiceImplTest {
  @Autowired
  AlgoMethodRepository algoMethodRepository;
  @Autowired
  SignalRepository signalRepository;
  @Autowired
  ParamRepository paramRepository;
  @Autowired
  SignalService signalService;

  @BeforeEach
  void init() {
    paramRepository.saveAll(List.of(Param.builder().id(1L).paramOne(1).paramTwo(60).build(),
        Param.builder().id(2L).paramOne(1).paramTwo(80).build(),
        Param.builder().id(3L).paramOne(1).paramTwo(90).build(),
        Param.builder().id(4L).paramOne(2).paramTwo(15).build()));
    List<Param> params = paramRepository.findAll();
    algoMethodRepository.saveAll(List.of(AlgoMethod.builder().id(1L).methodName("doAlgo").build(),
        AlgoMethod.builder().id(2L).methodName("cancelTrades").build(),
        AlgoMethod.builder().id(3L).methodName("reverse").build(),
        AlgoMethod.builder().id(4L).methodName("submitToMarket").build(),
        AlgoMethod.builder().id(5L).methodName("performCalc").build(),
        AlgoMethod.builder().id(6L).methodName("setUp").build(),
        AlgoMethod.builder().id(7L).methodName("setAlgoParam").build()));
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

  @Test
  void findBySignalNo() {
    List<Signal> bySignalNo = signalService.findBySignalNo(1);
    Assertions.assertEquals(4, bySignalNo.size());
    Assertions.assertEquals("setAlgoParam", bySignalNo.get(1).getAlgoMethod().getMethodName());
    Assertions.assertEquals(1, bySignalNo.get(1).getParam().getParamOne());
    Assertions.assertEquals(60, bySignalNo.get(1).getParam().getParamTwo());
  }

  @Test
  void handleSignal() {
    List<String> signalMethods = signalService.handleSignal(3);
    Assertions.assertEquals(4, signalMethods.size());
    Assertions.assertEquals("setAlgoParam", signalMethods.get(0));
    Assertions.assertEquals("submitToMarket", signalMethods.get(3));
  }
  @Test
  void handleSignal_notExist() {
    List<String> signalMethods = signalService.handleSignal(4);
    Assertions.assertEquals(0, signalMethods.size());
  }
}