package org.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class People {
    private int id;
    private String name;
    private String surname;
    private int age;
}
