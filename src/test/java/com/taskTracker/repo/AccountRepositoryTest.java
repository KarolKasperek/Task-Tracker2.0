package com.taskTracker.repo;

import com.taskTracker.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private  AccountRepository accountRepo;

    @Test
    @DisplayName("Account has been saved to repository.")
    public void testCreateUser() {

        // given
        Account user = new Account();
        user.setEmail("ravikumar@gmail.com");
        user.setPassword("ravi2020");

        // when
        Account savedUser = accountRepo.save(user);
        Account existUser = entityManager.find(Account.class, savedUser.getId());

        // then
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
