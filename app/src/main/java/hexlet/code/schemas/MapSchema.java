package hexlet.code.schemas;

import java.util.Map;

import static java.util.Objects.nonNull;

public final class MapSchema implements Schema {
    private final BaseSchema<Map<?, ?>> schema;

    public MapSchema() {
        schema = new BaseSchema<>();
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof Map)) {
            return false;
        }
        return schema.doCheck((Map<?, ?>) value);
    }

    public MapSchema required() {
        schema.setNotNullRequired();
        return this;
    }

    public MapSchema sizeof(int minSize) {
        schema.addTest(map -> map.size() == minSize);
        return this;
    }

    public MapSchema shape(Map<?, Schema> requirements) {
        schema.addTest(map -> isValidInside(map, requirements));
        return this;
    }

    private boolean isValidInside(Map<?, ?> data, Map<?, Schema> requirements) {
        return requirements.entrySet().stream()
                .allMatch(schemaEntry -> {
                    var requiredPropertyName = schemaEntry.getKey();
                    var propertyValue = data.get(requiredPropertyName);
                    var valueRequirements = schemaEntry.getValue();
                    return data.containsKey(requiredPropertyName) && valueRequirements.isValid(propertyValue);
                });
    }
}
