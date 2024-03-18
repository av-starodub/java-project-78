package hexlet.code.schema;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema() {
        super(value -> (Map<?, ?>) value, value -> value instanceof Map<?, ?>);
    }

    public MapSchema required() {
        setNotNull();
        return this;
    }

    public MapSchema sizeof(int validMapSize) {
        addTest(map -> map.size() == validMapSize);
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema<?>> schemaMap) {
        addTest(map -> isValidInside(map, schemaMap));
        return this;
    }

    private boolean isValidInside(Map<?, ?> data, Map<?, BaseSchema<?>> schemaMap) {
        return schemaMap.entrySet().stream()
                .allMatch(schemaEntry -> {
                    var requiredPropertyName = schemaEntry.getKey();
                    var propertyValue = data.get(requiredPropertyName);
                    var schema = schemaEntry.getValue();
                    return data.containsKey(requiredPropertyName) && schema.isValid(propertyValue);
                });
    }
}
