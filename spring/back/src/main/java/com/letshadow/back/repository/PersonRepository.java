package com.letshadow.back.repository;

import com.letshadow.back.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);
    List<Person> findByBirthdayBetween(LocalDate startDate,LocalDate endDate);
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday ")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);
    @Query(value="select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();
}
