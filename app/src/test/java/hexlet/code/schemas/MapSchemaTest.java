package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
}
