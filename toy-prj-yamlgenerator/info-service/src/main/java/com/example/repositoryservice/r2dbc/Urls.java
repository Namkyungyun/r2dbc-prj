package com.example.repositoryservice.r2dbc;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Getter
@Setter
@Table("URLS")
@Builder
@ToString

public class Urls implements Serializable {
    @Id
    @Column("id")
    private Long id;

    @Column("url")
    private String url;

    @Column("search")
    private String search;

    @Column("description")
    private String description;

    @Column("crud")
    private String crud;

    @Column("type")
    private String type;

    @Column("input_body")
    private String input_body;

    public Urls() {

    }
    private Urls(Long id, String url) {
        this.id= id;
        this.url = url;
    }

    public Urls(Urls urls) {

    }

    public Urls(Long id, String url, String search,
                String description, String crud,
                String type, String input_body) {
        this.id = id;
        this.url = url;
        this.search = search;
        this.description = description;
        this.crud = crud;
        this.type = type;
        this.input_body = input_body;
    }

}