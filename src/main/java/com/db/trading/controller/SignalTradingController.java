package com.db.trading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trading")
public class SignalTradingController {
  Logger logger = LoggerFactory.getLogger(SignalTradingController.class);

    @GetMapping("/{signal}")
    public @ResponseBody
    void handleSignal(@PathVariable Integer signal) {
      logger.info("signal =  {}", signal);
    }
  }