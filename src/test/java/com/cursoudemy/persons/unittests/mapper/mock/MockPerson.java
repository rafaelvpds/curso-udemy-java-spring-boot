package com.cursoudemy.persons.unittests.mapper.mock;

import java.util.ArrayList;
import java.util.List;

import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDtoV2;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonDtoV2 mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDtoV2> mockVOList() {
        List<PersonDtoV2> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2) == 0) ? 'M' : 'F');
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonDtoV2 mockVO(Integer number) {
        PersonDtoV2 person = new PersonDtoV2();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2) == 0) ? 'M' : 'F');
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}
