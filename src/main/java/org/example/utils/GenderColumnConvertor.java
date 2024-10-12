package org.example.utils;

import jakarta.persistence.AttributeConverter;

import static java.util.Objects.isNull;

public class GenderColumnConvertor implements AttributeConverter<Gender, String> {
    @Override
    public Gender convertToEntityAttribute(String columnName) {
        if (isNull(columnName)) {
            return null;
        }
        columnName = columnName.toUpperCase();
        for (Gender gender : Gender.values()) {
            if (gender.name().equals(columnName)) {
                return gender;
            }
        }
        return null;
    }

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return isNull(gender)
                ? null
                : gender.name().charAt(0) + gender.name().substring(1).toLowerCase();
    }
}
