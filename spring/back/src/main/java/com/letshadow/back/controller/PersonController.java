package com.letshadow.back.controller;

import com.letshadow.back.domain.Person;
import com.letshadow.back.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService personService;

    @GetMapping
    public Person getPerson(Long id){
        return personService.getPerson(id);
    }

}
