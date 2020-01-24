package com.letshadow.back.repository;

import com.letshadow.back.domain.Person;
import com.letshadow.back.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void 리포지토리_테스트(){
        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isEqualTo(5);
    }



    @Test
    void findByBirthdayTest(){
        List<Person> result = personRepository.findByMonthOfBirthday(8);
        assertThat(result.size()).isEqualTo(2);
    }
}