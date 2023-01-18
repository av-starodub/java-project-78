package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private final Validator validator;
    private final int minOfRange = 5;
    private final int maxOfRange = 10;

    public NumberSchemaTest() {
        validator = new Validator();
    }

    @Test
    public void shouldReturnTrueForNullValueIfMethodRequiredOff() {
        var schema = validator.number();
        var isValidNull = schema.positive().isValid(null);
        assertThat(isValidNull).isTrue();
    }

    @Test
    public void shouldReturnFalseForNullValueIfMethodRequiredOn() {
        var schema = validator.number();
        var isValidNull = schema.required().isValid(null);
        assertThat(isValidNull).isFalse();
    }

    @Test
    public void shouldReturnFalseForZero() {
        var schema = validator.number();
        var zero = 0;
        var isValidZero = schema.positive().isValid(zero);
        assertThat(isValidZero).isFalse();
    }

    @Test
    public void shouldReturnFalseForNegative() {
        var schema = validator.number();
        var negative = -10;
        var isValidNegative = schema.positive().isValid(negative);
        assertThat(isValidNegative).isFalse();
    }

    @Test
    public void shouldReturnTrueForPositiveNumber() {
        var schema = validator.number();
        var positive = 10;
        var isValidZero = schema.positive().isValid(positive);
        assertThat(isValidZero).isTrue();
    }

    @Test
    public void shouldReturnFalseIfArgumentDoesNotNumber() {
        var schema = validator.number();
        schema.required();
        var idValidAnotherDataType = schema.isValid("5");
        assertThat(idValidAnotherDataType).isFalse();
    }

    @Test
    public void shouldReturnTrueIfNumberInTargetRange() {
        var schema = validator.number();
        var inRange = 7;
        var isValidInRange = schema.range(minOfRange, maxOfRange).isValid(inRange);
        assertThat(isValidInRange).isTrue();
    }

    @Test
    public void shouldReturnTrueIfNumberEqualsMinOfRange() {
        var schema = validator.number();
        var isValidMinOfRange = schema.range(minOfRange, maxOfRange).isValid(minOfRange);
        assertThat(isValidMinOfRange).isTrue();
    }

    @Test
    public void shouldReturnTrueIfNumberEqualsMaxOfRange() {
        var schema = validator.number();
        var isValidMaxOfRange = schema.range(minOfRange, maxOfRange).isValid(maxOfRange);
        assertThat(isValidMaxOfRange).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNumberOutOfRange() {
        var schema = validator.number();
        var outOfRange = 11;
        var isValidOutOfRange = schema.range(minOfRange, minOfRange).isValid(outOfRange);
        assertThat(isValidOutOfRange).isFalse();
    }
}
