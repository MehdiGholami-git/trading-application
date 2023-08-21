package com.db.trading.controller;

import static org.mockito.Mockito.when;

import com.db.trading.service.SignalService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(SignalTradingController.class)
class SignalTradingControllerTest {

  Logger logger = LoggerFactory.getLogger(SignalTradingControllerTest.class);

  @MockBean
  SignalService signalService;
  @Autowired
  private MockMvc mockMvc;

  @Test
  void handleSignal() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trading/1").accept(MediaType.APPLICATION_JSON);
    when(signalService.handleSignal(1)).thenReturn(List.of("setUp", "setAlgoParam", "submitToMarket"));

    MvcResult result = mockMvc
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("setUp"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("setAlgoParam"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value("submitToMarket"))
        .andReturn();

    logger.info("response status ===> {}", result.getResponse().getStatus());

    Assertions.assertEquals(200, result.getResponse().getStatus());
  }
}