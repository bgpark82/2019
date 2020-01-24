package com.letshadow.back.service;

import com.letshadow.back.domain.Person;
import com.letshadow.back.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;


    @Test
    void merge_테스트() {
        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);
        result.forEach(System.out::println);
    }

    @Test
    void lazy_테스트() {
        Person person = personService.getPerson(3L);
        System.out.println(person);
    }



}