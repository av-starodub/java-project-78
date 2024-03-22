package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * The class for data validation.
 *
 * @param <T> data type.
 */
public final class GeneralSchema<T> implements BaseSchema<T> {
    private final List<Predicate<T>> schema;
    /**
     * for example if T is String - (obj) -> (String) obj.
     */
    private final Function<Object, T> doCast;
    private final Function<Object, Boolean> instanceOf;
    private boolean isNullValid;

    public GeneralSchema(Function<Object, T> doCst, Function<Object, Boolean> instOf) {
        schema = new ArrayList<>();
        doCast = doCst;
        instanceOf = instOf;
        isNullValid = true;
    }

    /**
     * Sets null as invalid.
     */
    public void setNotNull() {
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
    @Override
    public boolean isValid(Object value) {
        if (isNullValid && schema.isEmpty()) {
            return true;
        }
        return isNull(value)
                ? isNullValid
                : instanceOf.apply(value)
                && schema.stream().allMatch(validation -> validation.test(doCast.apply(value)));
    }
}
