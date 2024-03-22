package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private static final int MIN_LENGTH = 3;
    private StringSchema schema;

    @BeforeEach
    void init() {
        schema = new Validator().string();
    }

    @Test
    @DisplayName("checkRequired : until the required() is called, null and the empty string are valid")
    public void checkRequired() {
        // by default null and empty string are valid
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        // when
        schema.required();
        // then
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
    }

    @Test
    @DisplayName("checkMinLLength : the string must be equal to or longer than the value set by minLength()")
    void checkMinLLength() {
        schema.minLength(MIN_LENGTH);
        assertThat(schema.isValid("12")).isFalse();
        assertThat(schema.isValid("123")).isTrue();
    }

    @Test
    @DisplayName("checkContains : the string must contain a substring set by contains()")
    public void checkContains() {
        assertThat(schema.isValid("what?")).isTrue();

        schema.contains("wh");
        assertThat(schema.isValid("what?")).isTrue();

        schema.contains("what");
        assertThat(schema.isValid("what?")).isTrue();

        schema.contains("what does");
        assertThat(schema.isValid("what?")).isFalse();
    }

    @Test
    @DisplayName("checkBuildValidations : schema should accumulate validations")
    void checkBuildValidations() {
        var stringSchema = schema.required().minLength(MIN_LENGTH).contains("hex");
        assertThat(stringSchema.isValid(null)).isFalse();
        assertThat(stringSchema.isValid("1")).isFalse();
        assertThat(stringSchema.isValid("hex")).isTrue();
    }

    @Test
    @DisplayName("checkInvalidDataType : should return false when data type is invalid")
    public void checkInvalidDataType() {
        assertThat(schema.isValid(1)).isTrue();
        schema.required();
        assertThat(schema.isValid(1)).isFalse();
    }
}
