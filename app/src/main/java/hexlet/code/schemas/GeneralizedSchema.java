package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * Generic class for data validation.
 *
 * @param <T> data type.
 */
public final class GeneralizedSchema<T> implements BaseSchema<T> {
    private final Map<String, Predicate<T>> schema;
    private final Function<Object, T> doCast;
    private final Function<Object, Boolean> instanceOf;
    private boolean isNullValid;

    /**
     * Constructor.
     *
     * @param doCst function to cast a value to type T.
     * @param instOf function to check that a value is an instance of T.
     */
    public GeneralizedSchema(Function<Object, T> doCst, Function<Object, Boolean> instOf) {
        schema = new HashMap<>();
        doCast = doCst;
        instanceOf = instOf;
        isNullValid = true;
    }

    public void setNotNull() {
        isNullValid = false;
    }

    public void addTest(String key, Predicate<T> test) {
        schema.put(key, test);
    }

    /**
     * Validation method.
     *
     * @param value data
     * @return true if the value has passed all assigned checks.
     */
    @Override
    public boolean isValid(Object value) {
        if (isNullValid && schema.isEmpty()) {
            return true;
        }
        return isNull(value)
                ? isNullValid
                : instanceOf.apply(value)
                && schema.values().stream().allMatch(validation -> validation.test(doCast.apply(value)));
    }
}
