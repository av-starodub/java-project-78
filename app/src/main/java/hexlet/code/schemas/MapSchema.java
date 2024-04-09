package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        setNotNull();
        return this;
    }

    public MapSchema sizeof(int minSize) {
        addTest("sizeof", map -> map.size() == minSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> requirements) {
        addTest("shape", map -> isValidInside(map, requirements));
        return this;
    }

    private boolean isValidInside(Map<?, ?> data, Map<?, BaseSchema<String>> requirements) {
        return requirements.entrySet().stream()
                .allMatch(schemaEntry -> {
                    var requiredPropertyName = schemaEntry.getKey();
                    var propertyValue = data.get(requiredPropertyName);
                    var valueRequirements = schemaEntry.getValue();
                    return data.containsKey(requiredPropertyName) && valueRequirements.isValid(propertyValue);
                });
    }

    @Override
    protected Map<?, ?> cast(Object obj) {
        return obj instanceof Map<?, ?> ? (Map<?, ?>) obj : null;
    }
}
