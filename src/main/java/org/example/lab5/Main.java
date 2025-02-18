package org.example.lab5;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        final Properties diConfig = new Properties();
        InputStream is = Main.class.getResourceAsStream(DIPropertiesPath);
        try {
            if (is == null) {
                System.out.println("There is no correct properties file");
                return;
            }
            diConfig.load(is);
            Injector injector = new Injector(diConfig);
            checkInjector(injector);
        } catch (IOException e) {
            System.out.printf("Error while reading properties file: %s\n", e);
        }
    }

    /**
     * Проверяет работу инектора, создавая экземпляр SomeBean и вызывая его метод foo.
     *
     * @param injector экземпляр Injector для внедрения зависимостей.
     */
    private static void checkInjector(Injector injector) {
        SomeBean someBean = new SomeBean();
        injector.inject(someBean);
        someBean.foo();
    }

    private static final String DIPropertiesPath = "/DI.properties";
}