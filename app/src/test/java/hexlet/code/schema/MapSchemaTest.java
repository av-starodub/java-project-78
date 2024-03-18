package hexlet.code.schema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest extends BaseSchemaTest<MapSchema> {

    MapSchemaTest() {
        super(new MapSchema());
    }

    @Test
    @DisplayName("checkSizeOf : should set minimum valid map size and check compliance with it")
    public void checkSizeOf() {
        var data = new HashMap<String, String>();

        // by default any map size is valid
        assertThat(schema.isValid(data)).isTrue();

        var schemaWithSizeOf = schema.sizeof(data.size() + 1);
        assertThat(schemaWithSizeOf.isValid(data)).isFalse();

        data.put("key", "value");
        assertThat(schemaWithSizeOf.isValid(data)).isTrue();
    }
    @Test
    @DisplayName("")
    void checkBuildValidations() {
        var requirements = schema.required();
    }

    @Test
    @DisplayName("checkShape : should set the requirements and check the data inside the card according to them")
    public void checkShape() {
        Map<String, BaseSchema<?>> requirements = new HashMap<>();
        requirements.put("name", validator.string().required());
        requirements.put("age", validator.number().positive());

        var schemaWithShape = schema.shape(requirements);

        Map<String, Object> validPersonData = Map.of("name", "Kolya", "age", 1);
        assertThat(schemaWithShape.isValid(validPersonData)).isTrue();

        Map<String, Object> invalidNamePersonData = Map.of("name", "", "age", 1);
        assertThat(schemaWithShape.isValid(invalidNamePersonData)).isFalse();

        Map<String, Object> invalidAgePersonData = Map.of("name", "Kolya", "age", -1);
        assertThat(schemaWithShape.isValid(invalidAgePersonData)).isFalse();

        Map<String, Object> invalidAmountOfPersonData = Map.of("name", "Kolya");
        assertThat(schemaWithShape.isValid(invalidAmountOfPersonData)).isFalse();
    }
}
