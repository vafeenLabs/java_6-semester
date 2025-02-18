package org.example.lab5;

class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }

    /**
     * Геттер для поля field1.
     *
     * @return значение поля field1.
     */
    public SomeInterface getField1() {
        return field1;
    }

    /**
     * Геттер для поля field2.
     *
     * @return значение поля field2.
     */
    public SomeOtherInterface getField2() {
        return field2;
    }
}
