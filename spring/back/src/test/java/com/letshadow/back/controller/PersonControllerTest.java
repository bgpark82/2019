package com.letshadow.back.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letshadow.back.domain.Person;
import com.letshadow.back.dto.Birthday;
import com.letshadow.back.dto.PersonDto;
import com.letshadow.back.repository.PersonRepository;
import com.letshadow.back.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).setMessageConverters(messageConverter).build();
    }


    @Test
    void getPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("martin"))
                .andExpect(jsonPath("hobby").isEmpty())
                .andExpect(jsonPath("address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1991-08-15"))
                //.andExpect(jsonPath("$.birthday.yearOfBirthday").value(1991))
                //.andExpect(jsonPath("$.birthday.monthOfBirthday").value(8))
                //.andExpect(jsonPath("$.birthday.dayOfBirthday").value(15))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                // 일년이 지나면 테스트가 꺠짐 -> 존재하는지만 확인
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.birthdayToday").isBoolean())
        ;


    }

    @Test
    void postPerson() throws Exception {
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1234-1234");

        mockMvc.perform(
                MockMvcRequestBuilders
                .post("/api/v1/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isCreated());
        Person result = personRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).get(0);
        assertAll(
            () -> assertThat(result.getName()).isEqualTo("martin"),
            () -> assertThat(result.getHobby()).isEqualTo("programming"),
            () -> assertThat(result.getAddress()).isEqualTo("판교"),
            () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
            () -> assertThat(result.getJob()).isEqualTo("programmer"),
            () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1234-1234")
        );
    }

    @Test
    void modifiedPerson() throws Exception {
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1234-1234");

        mockMvc.perform(MockMvcRequestBuilders
                    .put("/api/v1/person/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
        Person result = personRepository.findById(1L).get();
        assertAll(
            () -> assertThat(result.getName()).isEqualTo("martin"),
            () -> assertThat(result.getHobby()).isEqualTo("programming"),
            () -> assertThat(result.getAddress()).isEqualTo("판교"),
            () -> assertThat(result.getBirthday()).isEqualTo(LocalDate.now()),
            () -> assertThat(result.getJob()).isEqualTo("programmer"),
            () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1234-1234")
        );
    }

    @Test
    void modifyName() throws Exception{
        // 1. 리퀘스트 요청 확인
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/v1/person/1")
                .param("name","martinModified"))
                .andDo(print())
                .andExpect(status().isOk());
        // 2. 실제 데이터 변경 확인
        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");

    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
        // controller 메인 로직을 테스트 때문에 수정하는 것은 좋지 않음
        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person->person.getId().equals(1L)));
        System.out.println(personRepository.findPeopleDeleted());
    }

    @Test
    void checkJsonString() throws JsonProcessingException {
        PersonDto dto = PersonDto.of("martin",null,"판교",LocalDate.now(),null,null);
        System.out.println(">>> " + toJsonString(dto));
    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }

}