package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        setNotNull();
        addTest("required", string -> !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addTest("minLength", string -> string.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addTest("contains", string -> string.contains(substring));
        return this;
    }

    @Override
    protected String cast(Object obj) {
        return obj instanceof String ? (String) obj : null;
    }
}
