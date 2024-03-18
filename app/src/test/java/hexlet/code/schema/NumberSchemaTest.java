package hexlet.code.schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private static final int MIN_OF_RANGE = 5;
    private static final int MAX_OF_RANGE = 10;

    private NumberSchema schema;

    @BeforeEach
    void init() {
        schema = new Validator().number();
    }

    @Test
    @DisplayName("checkRequired : until the required() is called, null is valid")
    public void checkRequired() {
        // by default null and empty string is valid
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();
        // when
        schema.required();
        // then
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.positive().isValid(null)).isFalse();
    }

    @Test
    @DisplayName("checkPositive : should return false when the number is less than or equal to zero")
    public void checkPositive() {
        schema.positive();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(-1)).isFalse();
    }


    @Test
    @DisplayName("checkRange : should return true when number is in the range including bounds")
    public void checkRange() {
        schema.range(MIN_OF_RANGE, MAX_OF_RANGE);

        // check that start and end of range are valid
        assertThat(schema.isValid(MIN_OF_RANGE)).isTrue();
        assertThat(schema.isValid(MIN_OF_RANGE)).isTrue();

        var inRangeValue = MIN_OF_RANGE + 1;
        assertThat(schema.isValid(inRangeValue)).isTrue();

        var outOfRangeDownValue = MIN_OF_RANGE - 1;
        assertThat(schema.isValid(outOfRangeDownValue)).isFalse();

        var outOfRangeUpValue = MAX_OF_RANGE + 1;
        assertThat(schema.isValid(outOfRangeUpValue)).isFalse();
    }

    @Test
    @DisplayName("checkBuildValidations : schema should accumulate validations")
    void checkBuildValidations() {
        var numberSchema = schema.required().positive().range(MIN_OF_RANGE, MAX_OF_RANGE);
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(-1)).isFalse();
        assertThat(numberSchema.isValid(MAX_OF_RANGE)).isTrue();
        assertThat(numberSchema.isValid(MAX_OF_RANGE + 1)).isFalse();
    }

    @Test
    @DisplayName("checkInvalidDataType : should return false when data type is invalid")
    public void checkInvalidDataType() {
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(1.0)).isFalse();
    }
}
