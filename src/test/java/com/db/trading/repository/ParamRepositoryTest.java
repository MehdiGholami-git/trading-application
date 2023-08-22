package com.db.trading.repository;

import com.db.trading.model.entities.Param;
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
public class ParamRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ParamRepository paramRepository;

  @Test
  public void whenFindAll_thenReturnParam() {
    // given
    Param param = Param.builder().paramOne(1).paramTwo(16).build();
    entityManager.persist(param);
    entityManager.flush();

    // when
    List<Param> params = paramRepository.findAll();

    // then
    Assertions.assertEquals(params.get(0).getParamOne(), param.getParamOne());
  }
}