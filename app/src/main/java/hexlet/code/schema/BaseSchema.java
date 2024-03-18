package hexlet.code.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * Base class for implementing a data validation schema.
 *
 * @param <T> data type for validation function.
 */
public abstract class BaseSchema<T> {
    private final List<Predicate<T>> validations;
    private final Function<Object, T> doCast;
    private final Function<Object, Boolean> instanceOf;
    private boolean isNullValid;

    protected BaseSchema(Function<Object, T> dC, Function<Object, Boolean> insOf) {
        validations = new ArrayList<>();
        doCast = dC;
        instanceOf = insOf;
        isNullValid = true;
    }

    /**
     * Template method for data validation.
     *
     * @param value any.
     * @return true if the value has passed all assigned checks for a specific schema implementation.
     */
    public final boolean isValid(Object value) {
        if (isNull(value)) {
            return isNullValid;
        }
        if (!instanceOf.apply(value)) {
            return false;
        }
        return validations.stream().allMatch(validation -> validation.test(doCast.apply(value)));
    }

    protected final void addTest(Predicate<T> test) {
        validations.add(test);
    }

    protected final void setNotNull() {
        isNullValid = false;
    }
}
