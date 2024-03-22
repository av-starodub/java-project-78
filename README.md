## Валидатор данных

[![Actions Status](https://github.com/av-starodub/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/av-starodub/java-project-78/actions)
[![Java CI](https://github.com/av-starodub/java-project-78/actions/workflows/javaci.yml/badge.svg)](https://github.com/av-starodub/java-project-78/actions/workflows/javaci.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/c5080706082f1aa339c4/maintainability)](https://codeclimate.com/github/av-starodub/java-project-78/maintainability)
[![codecov](https://codecov.io/github/av-starodub/java-project-78/branch/main/graph/badge.svg?token=D6t7Qh4d9y)](https://codecov.io/github/av-starodub/java-project-78)

### Описание

Библиотека, с помощью которой можно проверять соответствие данных установленным требованиям. <br>
За основу для проекта взята библиотека [yup](https://github.com/jquense/yup). <br>
Интерфейс библиотеки сделан в декларативном стиле (DSL). <br>
Реализация базируется на паттерне Fluent interface.

### Использование

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

class DataValidationExample {

    public static void main(String[] args) {

        Validator validator = new Validator();

        // String
        StringSchema stringSchema = validator.string();

        stringSchema.isValid(null); // true by default for all validators
        stringSchema.isValid(""); // true by default for String

        stringSchema.required(); // sets null and empty string as invalid
        stringSchema.isValid(null); // false
        stringSchema.isValid(""); // false

        stringSchema.minLength(5).contains("wh"); // schema accumulate requirements
        stringSchema.isValid("what"); // false because "what".length() < 5
        stringSchema.isValid("valid length"); // false
        stringSchema.isValid("what does the fox say"); // true

        // Number
        NumberSchema numberSchema = validator.number();

        numberSchema.required().positive();

        numberSchema.isValid(null); // false
        numberSchema.isValid(0); // false
        numberSchema.isValid(-10); // false
        numberSchema.isValid(10); // true

        // Map
        MapSchema schema = validator.map();

        // check object Map 
        schema.required().sizeof(2);
        schema.isValid(new HashMap<>()); // false

        // check map inside
        // create requirements
        Map<String, BaseSchema<String>> requirements = new HashMap<>();
        requirements.put("firstName", validator.string().required());
        requirements.put("lastName", validator.string().required().minLength(2));

        // add to schema 
        schema.shape(requirements);

        // validate
        Map<String, Object> validPersonData = Map.of("firstName", "John", "lastName", "Smith");
        schema.isValid(validPersonData); // true

        Map<String, Object> invalidValuePersonData = Map.of("firstName", "", "lastName", "");
        schema.isValid(invalidValuePersonData); // false

        Map<String, Object> invalidValueLengthPersonData = Map.of("firstName", "John", "lastName", "A");
        schema.isValid(invalidValueLengthPersonData); // false

        Map<String, Object> notAllPersonData = Map.of("firstName", "John");
        schema.isValid(notAllPersonData); // false
    }
}
```
