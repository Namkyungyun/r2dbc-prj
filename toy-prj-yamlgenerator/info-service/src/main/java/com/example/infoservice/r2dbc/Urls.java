package com.example.infoservice.r2dbc;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("URLS")
@Builder
public class Url {
    @Id
    @Column("id")
    private Long id;

    @Column("url")
    private String url;

    @Column("connect")
    private String connect;

    @Column("description")
    private String description;

}
