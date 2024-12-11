package com.example.demo2.r2dbc;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("TEAM")
@Builder
public class Team {
    @Id
    @Column("id")
    private String id;
    @Column("team_name")
    private String name;

}
