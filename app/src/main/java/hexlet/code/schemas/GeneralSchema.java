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
    private boolean isNullValid;

    public GeneralSchema(Function<Object, T> doCst) {
        schema = new ArrayList<>();
        doCast = doCst;
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
    @Override
    public boolean isValid(Object value) {
        return isNull(value)
                ? isNullValid
                : schema.stream().allMatch(validation -> validation.test(doCast.apply(value)));
    }
}
