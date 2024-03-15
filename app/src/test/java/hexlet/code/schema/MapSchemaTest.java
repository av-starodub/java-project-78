package hexlet.code.schema;

import hexlet.code.Validator;
import hexlet.code.schema.base.Schema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {
    private final Validator validator;

    public MapSchemaTest() {
        validator = new Validator();
    }

    @Test
    public void shouldReturnTrueForNullValueIfMethodRequiredOff() {
        var schema = validator.map();
        var isValidNull = schema.isValid(null);
        assertThat(isValidNull).isTrue();
    }

    @Test
    public void shouldReturnFalseForAnotherInstance() {
        var schema = validator.map();
        var isValidSet = schema.isValid(new HashSet<>());
        assertThat(isValidSet).isFalse();
    }

    @Test
    public void shouldReturnFalseForNullValueIfMethodRequiredOn() {
        var schema = validator.map();
        var isValidNull = schema.required().isValid(null);
        assertThat(isValidNull).isFalse();
    }

    @Test
    public void shouldReturnTrueForMapInstanceValue() {
        var schema = validator.map();
        var isValidInstance = schema.isValid(new HashMap<>());
        assertThat(isValidInstance).isTrue();
    }

    @Test
    public void shouldReturnFalseIfMapSizeValueIsNotEqualWhichSetAsTarget() {
        var schema = validator.map();
        var data = new HashMap<String, String>();
        boolean isValidSize;

        data.put("key1", "value1");
        isValidSize = schema.isValid(data);
        assertThat(isValidSize).isTrue();

        var targetSize = 2;
        isValidSize = schema.sizeof(targetSize).isValid(data);
        assertThat(isValidSize).isFalse();

        data.put("key2", "value2");
        isValidSize = schema.isValid(data);
        assertThat(isValidSize).isTrue();
    }

    @Test
    public void shouldCorrectlyValidateAllDataInsideTheMap() {
        var schema = validator.map();
        var schemas = new HashMap<String, Schema>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());

        var human1 = new HashMap<String, Object>();
        human1.put("name", "Kolya");
        var human1Age = 100;
        human1.put("age", human1Age);

        var isValidHuman1 = schema.shape(schemas).isValid(human1);

        assertThat(isValidHuman1).isTrue();

        var human2 = new HashMap<String, Object>();
        human2.put("name", "Maya");
        human2.put("age", null);
        var isValidHuman2 = schema.isValid(human2);

        assertThat(isValidHuman2).isTrue();

        var human3 = new HashMap<String, Object>();
        human3.put("name", "");
        human3.put("age", null);
        var isValidHuman3 = schema.isValid(human3);

        assertThat(isValidHuman3).isFalse();

        var human4 = new HashMap<String, Object>();
        human4.put("name", "Valya");
        var human4Age = -5;
        human4.put("age", human4Age);
        var isValidHuman4 = schema.isValid(human4);

        assertThat(isValidHuman4).isFalse();
    }
}
