package hexlet.code.schema;

import hexlet.code.schema.base.BaseSchema;
import hexlet.code.schema.base.Schema;

import static java.util.Objects.nonNull;

public final class NumberSchema implements Schema {
    private final BaseSchema<Integer> schema;

    public NumberSchema() {
        schema = new BaseSchema<>();
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof Integer)) {
            return false;
        }
        return schema.doCheck((Integer) value);
    }

    public NumberSchema required() {
        schema.setNotNullRequired();
        return this;
    }

    public NumberSchema positive() {
        schema.addTest(number -> number > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        schema.addTest(number -> number >= start && number <= end);
        return this;
    }
}
