package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidatorTest {
    @Test
    public void isClassValidatorExist() {
        assertThat(new Validator()).isExactlyInstanceOf(Validator.class);
    }

    @Test
    public void shouldCreateStringSchemaInstance() {
        var validator = new Validator();
        StringSchema schema = validator.string();
        assertThat(schema).isExactlyInstanceOf(StringSchema.class);
    }
}
