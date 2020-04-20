package com.example.booksearch.repository;

import com.example.booksearch.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "transactionManager")
@Rollback
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 유저생성() {
        User user = new User("1","홍길동",passwordEncoder.encode("1111"));
        userRepository.save(user);

        User selectUser = userRepository.findById(user.getUserId()).get();

        assertTrue(user!=null);
        assertEquals(selectUser.getUserId(),user.getUserId());
    }

    @Test
    public void 유저삭제() {
        User user = new User("2", "홍길동", passwordEncoder.encode("1111"));
        userRepository.save(user);

        userRepository.deleteById(user.getUserId());

    }

    @Test
    public void 없는유저삭제() {
        boolean exceptionOccurred = false;
        try {
            userRepository.deleteById("none_id");
        }catch (EmptyResultDataAccessException e) {
            exceptionOccurred = true;
        }

        assertTrue(exceptionOccurred);

    }
}
