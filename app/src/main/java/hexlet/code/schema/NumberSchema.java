package hexlet.code.schema;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema() {
        super(value -> (Integer) value, value -> value instanceof Integer);
    }

    public NumberSchema required() {
        setNotNull();
        return this;
    }

    public NumberSchema positive() {
        addTest(number -> number > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        addTest(number -> number >= start && number <= end);
        return this;
    }
}
