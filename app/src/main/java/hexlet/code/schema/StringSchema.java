package hexlet.code.schema;

import hexlet.code.schema.base.BaseSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public final class StringSchema extends BaseSchema<String> {
    private final List<Predicate<String>> schema;

    public StringSchema() {
        schema = new ArrayList<>();
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof String)) {
            return false;
        }
        return doCheck((String) value, schema);
    }

    /**
     * Sets null && empty string as invalid.
     *
     * @return StringSchema.
     */
    public StringSchema required() {
        setNotNull();
        schema.add(string -> !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minimum) {
        schema.add(string -> string.length() >= minimum);
        return this;
    }

    public StringSchema contains(String substring) {
        schema.add(string -> string.contains(substring));
        return this;
    }
}
