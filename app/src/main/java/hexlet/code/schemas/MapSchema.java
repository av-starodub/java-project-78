package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public final class MapSchema extends AbstractSchema<Map> {
    private Map<String, BaseSchema> schemas;

    public MapSchema() {
        super();
        schemas = new HashMap<>();
    }

    @Override
    public boolean isValid(Object data) {
        if (!super.isValid(data)) {
            return false;
        }
        Map dataMap = doCast(data);
        for (var schema : schemas.entrySet()) {
            var schemaKey = schema.getKey();
            var validation = schema.getValue();
            var dataValue = dataMap.get(schemaKey);
            var nonValidValue = !validation.isValid(dataValue);
            if (nonValidValue) {
                return false;
            }
        }
        return true;
    }

    public MapSchema required() {
        isValidNull = false;
        return this;
    }

    public MapSchema sizeof(int validMapSize) {
        validations.add(map -> map.size() == validMapSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> requirements) {
        schemas = Map.copyOf(requirements);
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
