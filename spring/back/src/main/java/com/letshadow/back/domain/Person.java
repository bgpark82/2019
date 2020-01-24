package com.letshadow.back.domain;

import com.letshadow.back.dto.Birthday;
import com.letshadow.back.dto.PersonDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted=false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;


    // int는 primitive 타입이므로 null을 가질 수 없다.
    public Integer getAge(){
        if(this.birthday != null){
            return LocalDate.now().getYear() - this.getBirthday().getYearOfBirthday() + 1;
        } else {
          return null;
        }
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(this.getBirthday().getYearOfBirthday(), this.getBirthday().getMonthOfBirthday(), this.getBirthday().getDayOfBirthday()));
    }

    public void set(PersonDto personDto){
        // int에 값이 없으면 0이기 때문
        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }
        if(!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }
        if(!StringUtils.isEmpty(personDto.getJob())){
            this.setJob(personDto.getJob());
        }
        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
        if(!StringUtils.isEmpty(personDto.getBirthday())){
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
        if(personDto.getBirthday() != null){
            this.setAddress(personDto.getAddress());
        }
    }
}
