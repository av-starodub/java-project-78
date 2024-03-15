package hexlet.code.schema;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema() {
        super();
    }

    public NumberSchema positive() {
        validations.add(number -> number > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        validations.add(number -> number >= start && number <= end);
        return this;
    }

    @Override
    protected boolean isInstanceOf(Object value) {
        return value instanceof Integer;
    }

    @Override
    protected Integer doCast(Object value) {
        return (Integer) value;
    }
}
