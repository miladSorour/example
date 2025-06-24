package org.milad.example.reflection;

import jakarta.persistence.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class EntityPopulator {

    public static void populateEntityUsingSetters(Object entity, Map<String, Object> rowData) {
        Class<?> clazz = entity.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name(); // DB column name

                if (!rowData.containsKey(columnName)) continue;

                Object value = rowData.get(columnName);

                String setterName = "set" + capitalize(field.getName());

                try {
                    Method setter = findSetter(clazz, setterName, field.getType());
                    if (setter != null) {
                        setter.invoke(entity, value);
                    } else {
                        throw new NoSuchMethodException("No setter found for: " + setterName);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to set value for column: " + columnName, e);
                }
            }
        }
    }

    private static Method findSetter(Class<?> clazz, String setterName, Class<?> paramType) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equalsIgnoreCase(setterName)
                    && method.getParameterCount() == 1
                    && method.getParameterTypes()[0].isAssignableFrom(paramType)) {
                return method;
            }
        }
        return null;
    }

    private static String capitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
