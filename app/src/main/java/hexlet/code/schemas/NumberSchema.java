package hexlet.code.schemas;

public final class NumberSchema extends AbstractSchema<Integer> {

    public NumberSchema() {
        super();
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

    @Override
    protected boolean nonValidValueType(Object value) {
        return !(value instanceof Integer);
    }

    @Override
    protected Integer doCast(Object value) {
        return (Integer) value;
    }
}
