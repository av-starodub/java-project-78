package hexlet.code;

import hexlet.code.schemas.NumberSchema;
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
        var stringSchema = validator.string();
        assertThat(stringSchema).isExactlyInstanceOf(StringSchema.class);
    }

    @Test
    public void shouldCreateNumberSchemaInstance() {
        var validator = new Validator();
        var numberSchema = validator.number();
        assertThat(numberSchema).isExactlyInstanceOf(NumberSchema.class);
    }
}
