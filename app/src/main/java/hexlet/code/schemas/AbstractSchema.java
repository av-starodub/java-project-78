package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractSchema<T> {
    protected final List<Predicate<T>> validations;
    protected boolean isValidNull;

    protected AbstractSchema() {
        validations = new ArrayList<>();
        isValidNull = true;
    }

    public final boolean isValid(Object value) {
        if (Objects.isNull(value)) {
            return isValidNull;
        }
        if (nonValidValueType(value)) {
            return false;
        }
        for (var validation : validations) {
            if (nonValidValue(validation, doCast(value))) {
                return false;
            }
        }
        return true;
    }

    protected abstract boolean nonValidValueType(Object value);

    protected abstract T doCast(Object value);

    private boolean nonValidValue(Predicate<T> validation, T value) {
        return !validation.test(value);
    }
}
