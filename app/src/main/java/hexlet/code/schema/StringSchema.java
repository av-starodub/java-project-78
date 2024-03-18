package hexlet.code.schema;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        super(value -> (String) value, value -> value instanceof String);
    }

    /**
     * Sets null && empty string as invalid.
     *
     * @return StringSchema.
     */
    public StringSchema required() {
        setNotNull();
        addTest(string -> !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minimum) {
        addTest(string -> string.length() >= minimum);
        return this;
    }

    public StringSchema contains(String substring) {
        addTest(string -> string.contains(substring));
        return this;
    }
}
