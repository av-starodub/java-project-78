## Валидатор данных

[![Actions Status](https://github.com/av-starodub/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/av-starodub/java-project-78/actions)
[![Java CI](https://github.com/av-starodub/java-project-78/actions/workflows/javaci.yml/badge.svg)](https://github.com/av-starodub/java-project-78/actions/workflows/javaci.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/c5080706082f1aa339c4/maintainability)](https://codeclimate.com/github/av-starodub/java-project-78/maintainability)

### Описание

Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных.
За основу для проекта взята библиотека [yup](https://github.com/jquense/yup). 
Интерфейс библиотеки для валидации – яркий пример DSL, специализированного языка, позволяющего декларативно
(описательно) описывать то, что вы хотите от кода. Код, написанный в таком стиле, читается значительно легче,
чем работа с прямым созданием объектов. Во многом этот подход базируется на паттерне fluent-интерфейс.

Пример использования:
```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// строки
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// объект Map с поддержкой проверки структуры
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```
