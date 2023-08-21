package com.db.trading.controller;

import com.db.trading.service.SignalService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trading")
public class SignalTradingController {
  Logger logger = LoggerFactory.getLogger(SignalTradingController.class);

  @Autowired
  SignalService signalService;

  @GetMapping("/{signal}")
  public @ResponseBody List<String> handleSignal(@PathVariable Integer signal) {
    logger.info("signal =  {}", signal);
    return signalService.handleSignal(signal);
  }
}