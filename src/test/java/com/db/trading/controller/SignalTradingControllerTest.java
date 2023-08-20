package com.db.trading.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(SignalTradingController.class)
class SignalTradingControllerTest {

  Logger logger = LoggerFactory.getLogger(SignalTradingController.class);

  @Autowired
  private MockMvc mockMvc;
  @Test
  void handleSignal() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/trading/1").accept(
        MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    logger.info("response status ===> {}", result.getResponse().getStatus());

    Assertions.assertEquals(200, result.getResponse().getStatus());
  }
}