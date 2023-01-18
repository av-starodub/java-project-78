package hexlet.code.schemas;

import java.util.Map;

@SuppressWarnings("rawtypes")
public final class MapSchema extends AbstractSchema<Map> {
    public MapSchema() {
        super();
    }

    public MapSchema required() {
        isValidNull = false;
        return this;
    }

    public MapSchema sizeof(int validMapSize) {
        validations.add(map -> map.size() == validMapSize);
        return this;
    }

    @Override
    protected boolean nonValidValueType(Object value) {
        return !(value instanceof Map);
    }

    @Override
    protected Map doCast(Object value) {
        return (Map) value;
    }
}
