package hexlet.code.schema;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        super();
    }

    /**
     * Sets null && empty string as invalid.
     *
     * @return StringSchema instance.
     */
    public StringSchema required() {
        super.required();
        validations.add(string -> !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minimum) {
        validations.add(string -> string.length() >= minimum);
        return this;
    }

    public StringSchema contains(String substring) {
        validations.add(string -> string.contains(substring));
        return this;
    }

    @Override
    protected boolean isInstanceOf(Object value) {
        return value instanceof String;
    }

    @Override
    protected String doCast(Object value) {
        return (String) value;
    }
}
