package org.example.lab5;

import java.lang.reflect.Field;
import java.util.Properties;

import java.lang.reflect.Field;
import java.util.Properties;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для внедрения зависимостей в объекты с использованием аннотации @AutoInjectable.
 */
public class Injector {
    private final Properties dependencyConfig;

    /**
     * Конструктор класса DependencyInjector.
     *
     * @param dependencyConfig свойства, содержащие конфигурацию внедрения зависимостей.
     */
    public Injector(Properties dependencyConfig) throws NullPointerException {
        if (dependencyConfig == null) {
            throw new NullPointerException("Конфигурация зависимостей не может быть null");
        }
        this.dependencyConfig = dependencyConfig;
    }

    /**
     * Метод для внедрения зависимостей в указанный объект.
     *
     * @param target объект, в который будут внедрены зависимости.
     * @param <T>    тип объекта.
     */
    public <T> void inject(T target) throws IllegalStateException {
        Class<?> targetClass = target.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();

        for (Field currentField : declaredFields) {
            if (currentField.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = currentField.getType();
                String implementationClassName = dependencyConfig.getProperty(fieldType.getName());
                if (implementationClassName != null) {
                    try {
                        Class<?> implementationClass = Class.forName(implementationClassName);
                        Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
                        currentField.setAccessible(true);
                        currentField.set(target, implementationInstance);
                    } catch (Exception e) {
                        throw new IllegalStateException("Ошибка при предоставлении зависимости для " + target.getClass().getName(), e);
                    }
                } else {
                    throw new IllegalStateException("Не объявлена реализация для " + fieldType.getName());
                }
            }
        }
    }
}
