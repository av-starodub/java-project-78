package hexlet.code.schemas;

import static java.util.Objects.nonNull;

public final class StringSchema implements BaseSchema<String> {
    private final GeneralSchema<String> schema;

    public StringSchema() {
        schema = new GeneralSchema<>(obj -> (String) obj);
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof String)) {
            return false;
        }
        return schema.isValid(value);
    }

    /**
     * Sets null and empty string as invalid.
     *
     * @return StringSchema.
     */
    public StringSchema required() {
        schema.setNotNullRequired();
        schema.addTest(string -> !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        schema.addTest(string -> string.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        schema.addTest(string -> string.contains(substring));
        return this;
    }
}
