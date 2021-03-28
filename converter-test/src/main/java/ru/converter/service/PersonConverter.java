package ru.converter.service;

import ru.converter.annotation.Convertor;
import ru.converter.dto.Person;

@Convertor
public interface PersonConverter {

    Person map(Object[] arr);

}
