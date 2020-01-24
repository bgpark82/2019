package com.letshadow.back.service;

import com.letshadow.back.domain.Block;
import com.letshadow.back.domain.Person;
import com.letshadow.back.repository.BlockRepository;
import com.letshadow.back.repository.PersonRepository;
import jdk.nashorn.internal.ir.BlockStatement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void cascade_테스트() {
        List<Person> result = personService.getPeopleExcludeBlocks();
        result.forEach(System.out::println);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("benny");
    }

    @Test
    void merge_테스트() {
        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);
        Person person = result.get(2);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());
        result.forEach(System.out::println);
    }

    @Test
    void lazy_테스트() {
        Person person = personService.getPerson(3L);
        System.out.println(person);
    }



}