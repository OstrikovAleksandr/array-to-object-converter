package ru.converter.dto;

import ru.converter.annotation.ConvertProperty;

import java.time.LocalDate;

public class Person {

    @ConvertProperty(index = 0)
    private Integer age;
    @ConvertProperty(index = 1)
    private String lastName;
    @ConvertProperty(index = 2)
    private String firstName;
    @ConvertProperty(index = 3)
    private String patronymic;
    @ConvertProperty(index = 4)
    private LocalDate birthDay;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
