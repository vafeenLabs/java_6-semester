package org.example.lab5;

import org.junit.jupiter.api.Test;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class InjectorTest {

    @Test
    public void injectInjectsDependency() {
        // Настраиваем зависимости
        final Properties properties = new Properties();
        properties.put("org.example.lab5.SomeInterface", "org.example.lab5.SomeImpl");
        properties.put("org.example.lab5.SomeOtherInterface", "org.example.lab5.SODoer");
        final Injector injector = new Injector(properties);
        final SomeBean someBean = new SomeBean();

        // Проверяем, что поле field1 изначально null
        assertNull(someBean.getField1(), "Поле field1 должно быть null до инъекции");

        // Выполняем инъекцию
        injector.inject(someBean);

        // Проверяем, что зависимость успешно внедрена
        assertNotNull(someBean.getField1(), "Зависимость должна быть внедрена");
        assertInstanceOf(SomeImpl.class, someBean.getField1(), "Внедрена должна быть реализация SomeImpl");
    }

    @Test
    public void injectWithNonExistentClassThrowsIllegalStateException() {
        // Настраиваем зависимости с несуществующим классом
        final Properties properties = new Properties();
        properties.put("org.example.lab5.SomeInterface", "org.example.lab5.NonExistentClass");
        final Injector injector = new Injector(properties);

        final SomeBean someBean = new SomeBean();

        // Проверяем, что выбрасывается IllegalStateException
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> injector.inject(someBean));
        assertTrue(exception.getMessage().contains("Ошибка при предоставлении зависимости для"),
                "Сообщение исключения должно содержать информацию о проблеме");
    }

    @Test
    public void injectWithEmptyPropertiesThrowsIllegalStateException() {
        // Настраиваем пустую конфигурацию зависимостей
        final Properties properties = new Properties();
        final Injector injector = new Injector(properties);

        final SomeBean someBean = new SomeBean();

        // Проверяем, что выбрасывается IllegalStateException
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> injector.inject(someBean));
        assertTrue(exception.getMessage().contains("Не объявлена реализация для"),
                "Сообщение исключения должно содержать информацию о недостающей реализации");
    }

    @Test
    public void constructorWithNullPropertiesThrowsNullPointerException() {
        // Проверяем, что конструктор с null конфигурацией выбрасывает NullPointerException
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Injector(null));
        assertEquals("Конфигурация зависимостей не может быть null", exception.getMessage());
    }

    @Test
    public void injectMultipleDependencies() {
        // Настраиваем зависимости для нескольких интерфейсов
        final Properties properties = new Properties();
        properties.put("org.example.lab5.SomeInterface", "org.example.lab5.SomeImpl");
        properties.put("org.example.lab5.SomeOtherInterface", "org.example.lab5.SODoer");

        final Injector injector = new Injector(properties);
        final SomeBean someBean = new SomeBean();

        // Выполняем инъекцию
        injector.inject(someBean);

        // Проверяем, что обе зависимости внедрены корректно
        assertNotNull(someBean.getField1(), "Поле field1 должно быть внедрено");
        assertNotNull(someBean.getField2(), "Поле field2 должно быть внедрено");

        assertInstanceOf(SomeImpl.class, someBean.getField1(), "Поле field1 должно быть экземпляром SomeImpl");
        assertInstanceOf(SODoer.class, someBean.getField2(), "Поле field2 должно быть экземпляром SODoer");
    }

    @Test
    public void injectInterfaceWithoutImplementationThrowsIllegalStateException() {
        // Настраиваем зависимости только для одного интерфейса (ожидаем исключение из-за второго)
        final Properties properties = new Properties();
        properties.put("org.example.lab5.SomeInterface", "org.example.lab5.SomeImpl"); //указываем только для одного интерфейса

        final Injector injector = new Injector(properties);
        final SomeBean someBean = new SomeBean();

        // Проверяем, что выбрасывается IllegalStateException
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> injector.inject(someBean));
        assertTrue(exception.getMessage().contains("Не объявлена реализация для org.example.lab5.SomeOtherInterface"),
                "Сообщение исключения должно содержать информацию о недостающей реализации SomeOtherInterface");
    }

    @Test
    public void injectDifferentImplementations() {
        // Настраиваем зависимости с разными реализациями
        final Properties properties = new Properties();
        properties.put("org.example.lab5.SomeInterface", "org.example.lab5.OtherImpl");
        properties.put("org.example.lab5.SomeOtherInterface", "org.example.lab5.SODoer");

        final Injector injector = new Injector(properties);
        final SomeBean someBean = new SomeBean();

        // Выполняем инъекцию
        injector.inject(someBean);

        // Проверяем, что внедрена правильная реализация
        assertNotNull(someBean.getField1(), "Поле field1 должно быть внедрено");
        assertInstanceOf(OtherImpl.class, someBean.getField1(), "Поле field1 должно быть экземпляром OtherImpl");
    }

    @Test
    public void injectWithoutAnnotationDoesNothing() {
        // Настраиваем зависимости для интерфейса без аннотации @AutoInjectable в классе полей
        final Properties properties = new Properties();

        final Injector injector = new Injector(properties);

        class NoAnnotationClass {
            private SomeInterface field;

            public SomeInterface getField() {
                return field;
            }
        }

        NoAnnotationClass noAnnotationClass = new NoAnnotationClass();

        // Выполняем инъекцию (ничего не должно произойти)
        injector.inject(noAnnotationClass);

        // Поле остается null, так как аннотация отсутствует
        assertNull(noAnnotationClass.getField(), "Поле без аннотации не должно изменяться после инъекции");
    }
}
