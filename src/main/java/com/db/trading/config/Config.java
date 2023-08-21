package com.db.trading.config;

import com.db.trading.lib.Algo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public Algo algo() {
    return new Algo();
  }
}