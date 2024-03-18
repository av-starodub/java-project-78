package hexlet.code.schema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest extends BaseSchemaTest<StringSchema> {
    private static final int MIN_LENGTH = 3;

    public StringSchemaTest() {
        super(new StringSchema());
    }

    @Test
    @DisplayName("checkRequiredEmptyString : ")
    public void checkRequiredEmptyString() {
        // by default null and empty string is valid
        assertThat(schema.isValid("")).isTrue();
        // when
        var schemaWithRequired = schema.required();
        // then
        assertThat(schemaWithRequired.isValid("")).isFalse();
    }

    @Test
    @DisplayName("checkMinLLength : ")
    void checkMinLLength() {
        var schemaWithMinLength = schema.minLength(MIN_LENGTH);
        assertThat(schemaWithMinLength.isValid("12")).isFalse();
        assertThat(schemaWithMinLength.isValid("123")).isTrue();
    }

    @Test
    @DisplayName("checkContains : ")
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
    @DisplayName("")
    void checkBuildValidations() {
        var requirements = schema.required().minLength(MIN_LENGTH).contains("hex");
    }
}
