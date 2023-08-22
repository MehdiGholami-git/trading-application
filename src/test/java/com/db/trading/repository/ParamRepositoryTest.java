package com.db.trading.repository;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParamRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @MockBean
  private Signal signal;

  @Autowired
  private ParamRepository paramRepository;

  @Test
  public void whenFindBySignalId_thenReturnSignal() {
    // given
    Param param = Param.builder().paramOne(1).paramTwo(16).signal(signal).build();
    entityManager.persist(param);
    entityManager.flush();

    // when
    List<Param> params = paramRepository.findAll();

    // then
    Assertions.assertEquals(params.get(0).getParamOne(), param.getParamOne());
  }
}