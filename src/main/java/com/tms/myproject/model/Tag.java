package com.tms.myproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Tag extends BaseModel{

    @Column
    private String tagName;

}
