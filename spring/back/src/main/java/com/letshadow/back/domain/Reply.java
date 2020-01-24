package com.letshadow.back.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@Builder
public class Reply {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
}
