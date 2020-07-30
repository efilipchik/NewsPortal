package com.tms.myproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class News extends BaseModel{

    @Column
    private String theme;

    @Column
    private String content;

    @Column
    private Double rating;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Tag tag;

}
