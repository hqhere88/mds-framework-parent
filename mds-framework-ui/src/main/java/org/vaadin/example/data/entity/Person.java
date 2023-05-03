package org.vaadin.example.data.entity;

import org.vaadin.example.data.AbstractEntity;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

public class Person {
    @NotEmpty
    private String name = "";

    @NotEmpty
    private int earOfBirth = 0;

    @OneToMany(mappedBy = "person")
    private List<Person> persons = new LinkedList<>();

    public Person(String name, int earOfBirth) {
        this.name = name;
        this.earOfBirth = earOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return earOfBirth;
    }

    public void setYearOfBirth(int earOfBirth) {this.earOfBirth = earOfBirth;}

    public List<Person> getPersons() {
        return persons;
    }

}
