package com.letshadow.back.service;

import com.letshadow.back.domain.Person;
import com.letshadow.back.dto.Birthday;
import com.letshadow.back.dto.PersonDto;
import com.letshadow.back.exception.PersonNotFoundException;
import com.letshadow.back.exception.RenameNotPermittedException;
import com.letshadow.back.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @Test
    void getAll() {
        when(personRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.newArrayList(getPerson("martin"),getPerson("dennis"),getPerson("tony"))));
        Page<Person> result = personService.getAll(PageRequest.of(0,3));
        assertThat(result.getNumberOfElements()).isEqualTo(3);
        assertThat(result.getContent().get(0).getName()).isEqualTo("martin");
        assertThat(result.getContent().get(1).getName()).isEqualTo("dennis");
        assertThat(result.getContent().get(2).getName()).isEqualTo("tony");
    }

    private Person getPerson(String name){
        return Person.builder().name(name).build();
    }

    @Test
    void GetPeopleByName() {
        when(personRepository.findByName("martin"))
                .thenReturn(Lists.newArrayList(mockPerson("martin")));

        List<Person> result = personService.getPeopleByName("martin");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }



    @Test
    void getPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(mockPerson("martin")));

        Person newPerson = personService.getPerson(1L);
        assertThat(newPerson.getName()).isEqualTo("martin");
    }

    @Test
    void getPersonIfNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        Person person = personService.getPerson(1L);
        assertThat(person).isNull();
    }

    @Test
    void put(){
        personService.put(mockPersonDto());
        verify(personRepository, times(1)).save(argThat(new IsPersonWIllBeInserted()));
    }

    @Test
    void modifyIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        // Runtime Exception이 발생할 것이다.
        assertThrows(PersonNotFoundException.class, ()-> personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modifyIfNameIsDifferent(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(mockPerson("tony")));
        assertThrows(RenameNotPermittedException.class,()->personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modify() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(mockPerson("martin")));
        personService.modify(1L, mockPersonDto());
//        verify(personRepository, times(1)).save(any(Person.class));
        verify(personRepository, times(1)).save(argThat(new IsPersonWIllBeUpdated()));
    }

    @Test
    void modifyByNameIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, () -> personService.modify(1L, "daniel"));
    }

    @Test
    void modifyByName(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(mockPerson("martin")));
        personService.modify(1L,"daniel");
        verify(personRepository,times(1)).save(argThat(new IsNameWillBeUpdated()));
    }

    @Test
    void deleteIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class,()-> personService.delete(1L));
    }

    @Test
    void delete(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(mockPerson("martin")));
        personService.delete(1L);
        verify(personRepository,times(1)).save(argThat(new IsPersonWillBeDeleted()));
    }


    private PersonDto mockPersonDto(){
        return PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");
    }

    private Person mockPerson(String name) {
        return Person.builder()
                .name(name).build();
    }

    private static class IsPersonWIllBeInserted implements ArgumentMatcher<Person> {

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"programming")
                    && equals(person.getAddress(),"판교")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"programmer")
                    && equals(person.getPhoneNumber(),"010-1111-2222");
        }

        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsPersonWIllBeUpdated implements ArgumentMatcher<Person> {

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                && equals(person.getHobby(),"programming")
                && equals(person.getAddress(),"판교")
                && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                && equals(person.getJob(),"programmer")
                && equals(person.getPhoneNumber(),"010-1111-2222");
        }

        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsNameWillBeUpdated implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.getName().equals("daniel");
        }
    }

    private static class IsPersonWillBeDeleted implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.isDeleted();
        }
    }

}