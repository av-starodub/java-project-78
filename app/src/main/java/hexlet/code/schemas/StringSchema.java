package hexlet.code.schemas;

public final class StringSchema implements BaseSchema<String> {
    private final GeneralizedSchema<String> schema;

    public StringSchema() {
        schema = new GeneralizedSchema<>(obj -> (String) obj, obj -> obj instanceof String);
    }

    @Override
    public boolean isValid(Object value) {
        return schema.isValid(value);
    }

    /**
     * Sets null and empty string as invalid.
     *
     * @return StringSchema.
     */
    public StringSchema required() {
        schema.setNotNull();
        schema.addTest("required", string -> !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        schema.addTest("minLength", string -> string.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        schema.addTest("contains", string -> string.contains(substring));
        return this;
    }
}
