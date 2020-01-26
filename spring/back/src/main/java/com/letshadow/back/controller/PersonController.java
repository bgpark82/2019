package com.letshadow.back.controller;

import com.letshadow.back.domain.Person;
import com.letshadow.back.dto.PersonDto;
import com.letshadow.back.exception.PersonNotFoundException;
import com.letshadow.back.exception.RenameNotPermittedException;
import com.letshadow.back.exception.dto.ErrorResponse;
import com.letshadow.back.repository.PersonRepository;
import com.letshadow.back.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@Slf4j
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    // entity를 request param으로 받는 것은 좋지 않다.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void putPerson(@RequestBody PersonDto personDto){
        personService.put(personDto);
     }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.modify(id, personDto);
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id,name);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }


}
