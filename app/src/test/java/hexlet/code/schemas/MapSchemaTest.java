package hexlet.code.schemas;

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
        // by default
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.sizeof(2).isValid(null)).isTrue();
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
        var requirements = new HashMap<String, BaseSchema<String>>();
        requirements.put("firstName", validator.string().required());
        requirements.put("lastName", validator.string().required().minLength(2));

        // when
        schema.shape(requirements);

        // then
        Map<String, Object> validPersonData = Map.of("firstName", "John", "lastName", "Smith");
        assertThat(schema.isValid(validPersonData)).isTrue();

        Map<String, Object> invalidValuePersonData = Map.of("name", "", "lastName", "");
        assertThat(schema.isValid(invalidValuePersonData)).isFalse();

        Map<String, Object> invalidValueLengthPersonData = Map.of("name", "John", "lastName", "A");
        assertThat(schema.isValid(invalidValueLengthPersonData)).isFalse();

        Map<String, Object> notAllPersonData = Map.of("name", "John");
        assertThat(schema.isValid(notAllPersonData)).isFalse();
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
    }
}
