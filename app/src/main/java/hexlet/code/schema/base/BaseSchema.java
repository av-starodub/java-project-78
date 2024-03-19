package hexlet.code.schema.base;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * Base class for implementing a data validation schema.
 *
 * @param <T> data type for validation.
 */
public abstract class BaseSchema<T> implements Schema {
    private boolean isNullValid;

    protected BaseSchema() {
        isNullValid = true;
    }

    protected final void setNotNull() {
        isNullValid = false;
    }

    /**
     * Method for data validation.
     *
     * @param schema value requirements
     * @param value  value to check
     * @return true if the value has passed all assigned checks for a specific schema implementation.
     */
    protected final boolean doCheck(T value, Collection<Predicate<T>> schema) {
        return isNull(value)
                ? isNullValid
                : schema.stream().allMatch(validation -> validation.test(value));
    }
}
