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

    public MapSchema shape(Map<?, BaseSchema<?>> setOfSchemas) {
        addTest(map -> checkInside(map, setOfSchemas));
        return this;
    }

    private boolean checkInside(Map<?, ?> data, Map<?, BaseSchema<?>> setOfSchemas) {
        return setOfSchemas.entrySet().stream()
                .allMatch(e -> {
                    var propertyName = e.getKey();
                    var propertyValue = data.get(propertyName);
                    var schema = e.getValue();
                    return data.containsKey(propertyName) && schema.isValid(propertyValue);
                });
    }
}
