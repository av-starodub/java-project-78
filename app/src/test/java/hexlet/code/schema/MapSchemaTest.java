package hexlet.code.schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {
    private MapSchema schema;

    @BeforeEach
    void init() {
        schema = new Validator().map();
    }

    @Test
    @DisplayName("checkRequired : until the required() is called, null is valid")
    public void checkRequired() {
        // by default null and empty string is valid
        assertThat(schema.isValid(null)).isTrue();
        // when
        schema.required();
        // then
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    @DisplayName("checkSizeOf : should set minimum valid map size and check compliance with it")
    public void checkSizeOf() {
        var data = new HashMap<String, String>();

        // by default any map size is valid
        assertThat(schema.isValid(data)).isTrue();

        schema.sizeof(data.size() + 1);
        assertThat(schema.isValid(data)).isFalse();

        data.put("key", "value");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    @DisplayName("checkShape : should set the requirements and check the data inside the map according to them")
    public void checkShape() {
        var validator = new Validator();

        // given requirements for map checking
        var requirements = new HashMap<String, BaseSchema<?>>();
        requirements.put("name", validator.string().required());
        requirements.put("age", validator.number().positive());

        // when
        schema.shape(requirements);

        // then
        Map<String, Object> validPersonData = Map.of("name", "Kolya", "age", 1);
        assertThat(schema.isValid(validPersonData)).isTrue();

        Map<String, Object> invalidNamePersonData = Map.of("name", "", "age", 1);
        assertThat(schema.isValid(invalidNamePersonData)).isFalse();

        Map<String, Object> invalidAgePersonData = Map.of("name", "Kolya", "age", -1);
        assertThat(schema.isValid(invalidAgePersonData)).isFalse();

        Map<String, Object> invalidAmountOfPersonData = Map.of("name", "Kolya");
        assertThat(schema.isValid(invalidAmountOfPersonData)).isFalse();
    }

    @Test
    @DisplayName("checkBuildValidations : schema should accumulate validations")
    void checkBuildValidations() {
        var mapSchema = schema.required().sizeof(1).shape(Map.of("key", new StringSchema().required()));
        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(Map.of())).isFalse();
        assertThat(mapSchema.isValid(Map.of("key", ""))).isFalse();
    }

    @Test
    @DisplayName("checkInvalidDataType : should return false when data type is invalid")
    public void checkInvalidDataType() {
        assertThat(schema.isValid(new HashSet<>())).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }
}
