package hexlet.code.schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseSchemaTest<T extends BaseSchema<?>> {
    protected final Validator validator;
    protected T schema;

    protected BaseSchemaTest(T specificSchema) {
        validator = new Validator();
        schema = specificSchema;
    }
    @Test
    @DisplayName("checkRequired : ")
    public void checkRequiredNotNull() {
        // by default null and empty string is valid
        assertThat(schema.isValid(null)).isTrue();
        // when
        //var schemaWithRequired = schema.required();
        // then
        //assertThat(schemaWithRequired.isValid(null)).isFalse();
    }
}
