package com.letshadow.back.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String title;
    private String content;
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Reply reply;
}
