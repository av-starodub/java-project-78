package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * Generic class for data validation.
 *
 * @param <T> data type.
 */
public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> schema;
    private boolean isNullValid;

    protected BaseSchema() {
        schema = new LinkedHashMap<>();
        isNullValid = true;
    }

    protected final void setNotNull() {
        isNullValid = false;
    }

    protected final void addTest(String key, Predicate<T> test) {
        schema.put(key, test);
    }

    public final boolean isValid(Object value) {
        if (isNull(value)) {
            return isNullValid;
        }
        for (var predicate : schema.values()) {
            T castedValue = cast(value);
            if (isNull(castedValue) || !predicate.test(castedValue)) {
                return false;
            }
        }
        return true;
    }

    protected abstract T cast(Object obj);
}
