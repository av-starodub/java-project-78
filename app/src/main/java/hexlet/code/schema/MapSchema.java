package hexlet.code.schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    /**
     * Rules for checking values inside the map.
     *
     * String key - object property name.
     * Schema value - requirements for property value validation.
     */
    private Map<String, BaseSchema<?>> requirements;

    public MapSchema() {
        super();
    }

    public MapSchema sizeof(int validMapSize) {
        validations.add(map -> map.size() == validMapSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> setOfSchemas) {
        this.requirements = new HashMap<>(setOfSchemas);
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!super.isValid(data)) {
            return false;
        }
        if (Objects.nonNull(requirements)) {
            Map<?, ?> properties = doCast(data);
            return checkInside(properties);
        }
        return true;
    }

    private boolean checkInside(Map<?, ?> properties) {
        for (var schema : requirements.entrySet()) {
            var propertyName = schema.getKey();
            var value = properties.get(propertyName);
            var validation = schema.getValue();
            if (!validation.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean isInstanceOf(Object value) {
        return value instanceof Map;
    }

    @Override
    protected Map<?, ?> doCast(Object value) {
        return (Map<?, ?>) value;
    }
}
