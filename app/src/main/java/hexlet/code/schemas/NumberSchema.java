package hexlet.code.schemas;

import static java.util.Objects.nonNull;

public final class NumberSchema implements BaseSchema<Integer> {
    private final GeneralSchema<Integer> schema;

    public NumberSchema() {
        schema = new GeneralSchema<>(obj -> (Integer) obj);
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof Integer)) {
            return false;
        }
        return schema.isValid(value);
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
