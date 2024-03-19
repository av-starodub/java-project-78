package hexlet.code.schema.base;

@FunctionalInterface
public interface Schema {

    boolean isValid(Object value);

}
