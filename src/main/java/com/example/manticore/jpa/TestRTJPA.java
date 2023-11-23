package com.example.manticore.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "testrt")
public class TestRTJPA {
    private String title;

    private String content;

    private Integer gid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
