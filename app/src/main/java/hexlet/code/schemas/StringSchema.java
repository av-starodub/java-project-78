package hexlet.code.schemas;

public final class StringSchema extends AbstractSchema<String> {
    public StringSchema() {
        super(String.class);
    }

    public void required() {
        isValidNull = false;
        validations.add(string -> !string.isEmpty());
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
    protected String doCast(Object value) {
        return (String) value;
    }
}
