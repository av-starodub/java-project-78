package hexlet.code.schema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest extends BaseSchemaTest<NumberSchema> {
    private static final int MIN_OF_RANGE = 5;
    private static final int MAX_OF_RANGE = 10;

    public NumberSchemaTest() {
        super(new NumberSchema());
    }

    @Test
    @DisplayName("")
    void checkBuildValidations() {
        //var requirements = schema.required();
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
        var negative = -1;
        var isValidNegative = schema.positive().isValid(negative);
        assertThat(isValidNegative).isFalse();
    }

    @Test
    @DisplayName("checkPositive : ")
    public void checkPositive() {
        var schema = validator.number();
        schema.positive();
        assertThat(schema.isValid(1)).isTrue();
    }

/*
    @Test
    public void shouldReturnFalseIfArgumentDoesNotNumber() {
        var schema = validator.number();
        schema.required();
        var idValidAnotherDataType = schema.isValid("5");
        assertThat(idValidAnotherDataType).isFalse();
    }
*/

    @Test
    public void shouldReturnTrueIfNumberInTargetRange() {
        var schema = validator.number();
        var inRange = MIN_OF_RANGE + 1;
        var isValidInRange = schema.range(MIN_OF_RANGE, MAX_OF_RANGE).isValid(inRange);
        assertThat(isValidInRange).isTrue();
    }

    @Test
    public void shouldReturnTrueIfNumberEqualsMinOfRange() {
        var schema = validator.number();
        var isValidMinOfRange = schema.range(MIN_OF_RANGE, MAX_OF_RANGE).isValid(MIN_OF_RANGE);
        assertThat(isValidMinOfRange).isTrue();
    }

    @Test
    public void shouldReturnTrueIfNumberEqualsMaxOfRange() {
        var schema = validator.number();
        var isValidMaxOfRange = schema.range(MIN_OF_RANGE, MAX_OF_RANGE).isValid(MAX_OF_RANGE);
        assertThat(isValidMaxOfRange).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNumberOutOfRangeUp() {
        var schema = validator.number();
        var outOfRangeUp = MAX_OF_RANGE + 1;
        var isValidOutOfRange = schema.range(MIN_OF_RANGE, MAX_OF_RANGE).isValid(outOfRangeUp);
        assertThat(isValidOutOfRange).isFalse();
    }
    @Test
    public void shouldReturnFalseIfNumberOutOfRangeDown() {
        var schema = validator.number();
        var outOfRangeDown = MIN_OF_RANGE - 1;
        var isValidOutOfRange = schema.range(MIN_OF_RANGE, MAX_OF_RANGE).isValid(outOfRangeDown);
        assertThat(isValidOutOfRange).isFalse();
    }

}
