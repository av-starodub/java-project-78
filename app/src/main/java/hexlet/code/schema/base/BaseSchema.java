package hexlet.code.schema.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * The class for data validation.
 *
 * @param <T> data type.
 */
public final class BaseSchema<T> {
    private final List<Predicate<T>> schema;
    private boolean isNullValid;

    public BaseSchema() {
        schema = new ArrayList<>();
        isNullValid = true;
    }

    /**
     * Sets null as invalid.
     */
    public void setNotNullRequired() {
        isNullValid = false;
    }

    public void addTest(Predicate<T> test) {
        schema.add(test);
    }

    /**
     * Validation method.
     *
     * @param value data
     * @return true if the value has passed all assigned checks.
     */
    public boolean doCheck(T value) {
        return isNull(value)
                ? isNullValid
                : schema.stream().allMatch(validation -> validation.test(value));
    }
}
