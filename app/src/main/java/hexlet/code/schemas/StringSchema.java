package hexlet.code.schemas;

public final class StringSchema extends AbstractSchema<String> {
    public StringSchema() {
        super();
    }

    public StringSchema required() {
        isValidNull = false;
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
    protected boolean nonValidValueType(Object value) {
        return !(value instanceof String);
    }

    @Override
    protected String doCast(Object value) {
        return (String) value;
    }
}
