package com.db.trading.repository;

import com.db.trading.model.entities.AlgoMethod;
import com.db.trading.model.entities.Param;
import com.db.trading.model.entities.Signal;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SignalRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private SignalRepository signalRepository;

  @Test
  public void whenFindBySignalNo_thenReturnSignal() {
    // given
    Param param = Param.builder().paramOne(1).paramTwo(16).build();
    entityManager.persist(param);
    entityManager.flush();

    AlgoMethod method = AlgoMethod.builder().methodName("teatMethod").build();
    entityManager.persist(method);
    entityManager.flush();

    Signal signal = Signal.builder().signalNo(3).algoMethod(method).param(param).build();
    entityManager.persist(signal);
    entityManager.flush();

    // when
    List<Signal> bySignalNo = signalRepository.findBySignalNo(3);

    // then
    Assertions.assertEquals(bySignalNo.get(0).getSignalNo(), signal.getSignalNo());
    Assertions.assertEquals(bySignalNo.get(0).getParam().getParamOne(), signal.getParam().getParamOne());
    Assertions.assertEquals(bySignalNo.get(0).getParam().getParamTwo(), signal.getParam().getParamTwo());
  }
}