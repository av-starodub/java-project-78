package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema {
    private final List<Predicate<String>> validations;

    public StringSchema() {
        validations = new ArrayList<>();
    }

    public boolean isValid(String string) {
        for (var isValid : validations) {
            if (!isValid.test(string)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(Object ignoredValue) {
        return false;
    }

    public void required() {
        validations.add(Objects::nonNull);
        validations.add(string -> !string.isEmpty());
    }

    public StringSchema minLength(int minimum) {
        validations.add(string -> string.length() >= minimum);
        return this;
    }

    public StringSchema contains(String substring) {
        validations.add(string -> string.contains(substring));
        return this;
    }
}
