package hexlet.code.schemas;

public final class NumberSchema implements BaseSchema<Integer> {
    private final GeneralizedSchema<Integer> schema;

    public NumberSchema() {
        schema = new GeneralizedSchema<>(obj -> (Integer) obj, obj -> obj instanceof Integer);
    }

    @Override
    public boolean isValid(Object value) {
        return schema.isValid(value);
    }

    public NumberSchema required() {
        schema.setNotNull();
        return this;
    }

    public NumberSchema positive() {
        schema.addTest("positive", number -> number > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        schema.addTest("range", number -> number >= start && number <= end);
        return this;
    }
}
