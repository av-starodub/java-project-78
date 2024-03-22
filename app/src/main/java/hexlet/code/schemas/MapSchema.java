package hexlet.code.schemas;

import java.util.Map;

import static java.util.Objects.nonNull;

public final class MapSchema implements BaseSchema<Map<?, ?>> {
    private final GeneralSchema<Map<?, ?>> schema;

    public MapSchema() {
        schema = new GeneralSchema<>(obj -> (Map<?, ?>) obj);
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof Map)) {
            return false;
        }
        return schema.isValid(value);
    }

    public MapSchema required() {
        schema.setNotNullRequired();
        return this;
    }

    public MapSchema sizeof(int minSize) {
        schema.addTest(map -> map.size() == minSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> requirements) {
        schema.addTest(map -> isValidInside(map, requirements));
        return this;
    }

    private boolean isValidInside(Map<?, ?> data, Map<?, BaseSchema<?>> requirements) {
        return requirements.entrySet().stream()
                .allMatch(schemaEntry -> {
                    var requiredPropertyName = schemaEntry.getKey();
                    var propertyValue = data.get(requiredPropertyName);
                    var valueRequirements = schemaEntry.getValue();
                    return data.containsKey(requiredPropertyName) && valueRequirements.isValid(propertyValue);
                });
    }
}
