package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractSchema<T> {
    protected final List<Predicate<T>> validations;
    protected boolean isValidNull;
    private final Class<T> validatorType;

    protected AbstractSchema(Class<T> type) {
        this.validations = new ArrayList<>();
        validatorType = type;
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

    protected abstract T doCast(Object value);

    private boolean nonValidValueType(Object value) {
        return !validatorType.equals(value.getClass());
    }

    private boolean nonValidValue(Predicate<T> validation, T value) {
        return !validation.test(value);
    }
}
