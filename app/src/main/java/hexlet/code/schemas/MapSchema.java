package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema implements BaseSchema<Map<?, ?>> {
    private final GeneralSchema<Map<?, ?>> schema;

    public MapSchema() {
        schema = new GeneralSchema<>(obj -> (Map<?, ?>) obj, obj -> obj instanceof Map<?, ?>);
    }

    @Override
    public boolean isValid(Object value) {
        return schema.isValid(value);
    }

    public MapSchema required() {
        schema.setNotNull();
        return this;
    }

    public MapSchema sizeof(int minSize) {
        schema.addTest(map -> map.size() == minSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> requirements) {
        schema.addTest(map -> isValidInside(map, requirements));
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
}
