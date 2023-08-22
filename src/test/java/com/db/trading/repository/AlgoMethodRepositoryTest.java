package com.db.trading.repository;

import com.db.trading.model.entities.AlgoMethod;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AlgoMethodRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private AlgoMethodRepository algoMethodRepository;

  @Test
  public void whenFindByMethodName_thenReturnAlgoMethod() {
    // given
    AlgoMethod method = AlgoMethod.builder().methodName("teatMethod").build();
    entityManager.persist(method);
    entityManager.flush();

    // when
    AlgoMethod methodFound = algoMethodRepository.findByMethodName("teatMethod");

    // then
    Assertions.assertEquals(methodFound.getMethodName(), method.getMethodName());
  }
}