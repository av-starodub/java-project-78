package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        setNotNull();
        return this;
    }

    public NumberSchema positive() {
        addTest("positive", number -> number > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        addTest("range", number -> number >= start && number <= end);
        return this;
    }

    @Override
    protected Integer cast(Object obj) {
        return obj instanceof Integer ? (Integer) obj : null;
    }
}
