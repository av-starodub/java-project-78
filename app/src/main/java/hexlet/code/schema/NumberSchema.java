package hexlet.code.schema;

import hexlet.code.schema.base.BaseSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public final class NumberSchema extends BaseSchema<Integer> {
    private final List<Predicate<Integer>> schema;

    public NumberSchema() {
        schema = new ArrayList<>();
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof Integer)) {
            return false;
        }
        return doCheck((Integer) value, schema);
    }

    public NumberSchema required() {
        setNotNull();
        return this;
    }

    public NumberSchema positive() {
        schema.add(number -> number > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        schema.add(number -> number >= start && number <= end);
        return this;
    }
}
