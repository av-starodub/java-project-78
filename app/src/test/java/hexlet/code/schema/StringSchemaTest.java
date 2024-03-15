package hexlet.code.schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private final Validator validator;

    public StringSchemaTest() {
        validator = new Validator();
    }

    @Test
    public void shouldReturnTrueForEmptyStringIfMethodRequiredOff() {
        var schema = validator.string();
        boolean isValidEmpty = schema.isValid("");
        assertThat(isValidEmpty).isTrue();
    }

    @Test
    public void shouldReturnFalseForEmptyStringIfMethodRequiredOn() {
        var schema = validator.string();
        schema.required();
        boolean isValidEmpty = schema.isValid("");
        assertThat(isValidEmpty).isFalse();
    }

    @Test
    public void shouldReturnTrueForNullValueIfMethodRequiredOff() {
        var schema = validator.string();
        boolean idValidNull = schema.isValid(null);
        assertThat(idValidNull).isTrue();
    }

    @Test
    public void shouldReturnFalseForNullValueIfMethodRequiredOn() {
        var schema = validator.string();
        schema.required();
        boolean idValidNull = schema.isValid(null);
        assertThat(idValidNull).isFalse();
    }

    @Test
    public void shouldReturnFalseIfArgumentDoesNotString() {
        var schema = validator.string();
        schema.required();
        boolean idValidAnotherDataType = schema.isValid(5);
        assertThat(idValidAnotherDataType).isFalse();
    }

    @Test
    public void shouldReturnTrueIfStringContainsSubstring() {
        var schema = validator.string();
        schema.required();
        boolean isContains = schema.contains("wh").isValid("what does the fox say");
        assertThat(isContains).isTrue();
    }

    @Test
    public void shouldReturnFalseIfStringShorterThanMinLength() {
        var schema = validator.string();
        schema.minLength(3);
        boolean isValidMinLength = schema.isValid("12");
        assertThat(isValidMinLength).isFalse();
    }

    @Test
    public void shouldReturnTrueIfStringEqualsMinLength() {
        var schema = validator.string();
        schema.minLength(3);
        boolean isValidMinLength = schema.minLength(3).isValid("123");
        assertThat(isValidMinLength).isTrue();
    }

    @Test
    public void shouldReturnTrueIfStringLongerMinLength() {
        var schema = validator.string();
        boolean isValidMinLength = schema.minLength(3).isValid("1234");
        assertThat(isValidMinLength).isTrue();
    }

    @Test
    public void shouldReturnFalseIfMethodContainsAlreadyReturnedFalseForThisString() {
        var schema = validator.string();
        schema.required();

        boolean isValidBeforeFalseContains = schema.isValid("what does the fox say");
        assertThat(isValidBeforeFalseContains).isTrue();

        boolean isContains = schema.contains("whatthe").isValid("what does the fox say");
        assertThat(isContains).isFalse();

        boolean isValidAfterFalseContains = schema.isValid("what does the fox say");
        assertThat(isValidAfterFalseContains).isFalse();
    }
}
