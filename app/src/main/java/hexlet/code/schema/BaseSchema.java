package hexlet.code.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Base class for implementing Schema.
 *
 * @param <T> data type to validate.
 */
public abstract class BaseSchema<T> {
    protected final List<Predicate<T>> validations;
    protected boolean isNullValid;

    protected BaseSchema() {
        validations = new ArrayList<>();
        isNullValid = true;
    }

    /**
     * The method sets null as invalid.
     *
     * @return a specific schema implementation.
     */
    public BaseSchema<T> required() {
        isNullValid = false;
        return this;
    }

    /**
     * Template method for data validation.
     *
     * @param value any.
     * @return true if the value has passed all assigned checks for a specific schema implementation.
     */
    public boolean isValid(Object value) {
        if (Objects.isNull(value)) {
            return isNullValid;
        }
        if (!isInstanceOf(value)) {
            return false;
        }
        for (var validation : validations) {
            var castedValue = doCast(value);
            if (!validation.test(castedValue)) {
                return false;
            }
        }
        return true;
    }

    protected abstract boolean isInstanceOf(Object value);

    protected abstract T doCast(Object value);
}
