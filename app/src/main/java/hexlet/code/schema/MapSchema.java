package hexlet.code.schema;

import hexlet.code.schema.base.BaseSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private final List<Predicate<Map<?, ?>>> schema;

    public MapSchema() {
        schema = new ArrayList<>();
    }

    @Override
    public boolean isValid(Object value) {
        if (nonNull(value) && !(value instanceof Map)) {
            return false;
        }
        return doCheck((Map<?, ?>) value, schema);
    }

    public MapSchema required() {
        setNotNull();
        return this;
    }

    public MapSchema sizeof(int validMapSize) {
        schema.add(map -> map.size() == validMapSize);
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema<?>> schemaMap) {
        schema.add(map -> isValidInside(map, schemaMap));
        return this;
    }

    private boolean isValidInside(Map<?, ?> data, Map<?, BaseSchema<?>> schemaMap) {
        return schemaMap.entrySet().stream()
                .allMatch(schemaEntry -> {
                    var requiredPropertyName = schemaEntry.getKey();
                    var propertyValue = data.get(requiredPropertyName);
                    var valueRequirements = schemaEntry.getValue();
                    return data.containsKey(requiredPropertyName) && valueRequirements.isValid(propertyValue);
                });
    }
}
