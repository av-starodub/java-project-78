package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema {
    private final List<Predicate<Integer>> validations;

    private boolean isValidNull;

    public NumberSchema() {
        validations = new ArrayList<>();
        isValidNull = true;
    }

    public boolean isValid(Integer number) {
        if (Objects.isNull(number)) {
            return isValidNull;
        }
        for (var validation : validations) {
            if (!validation.test(number)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(Object ignoredValue) {
        return false;
    }

    public NumberSchema required() {
        isValidNull = false;
        return this;
    }

    public NumberSchema positive() {
        validations.add(number -> number > 0);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        validations.add(number -> number >= min && number <= max);
        return this;
    }
}
