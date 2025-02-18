package org.example.lab1;

/**
 * Класс LinkedList представляет собой реализацию связанного списка.
 *
 * @param <T> тип элементов, которые будут храниться в списке.
 */
public class MyLinkedList<T> {
    Node<T> head;

    /**
     * Внутренний класс Node представляет узел связанного списка.
     *
     * @param <T> тип данных, хранящихся в узле.
     */
    static class Node<T> {
        T data; // Данные узла
        Node<T> next; // Ссылка на следующий узел

        /**
         * Конструктор для создания узла с заданными данными.
         *
         * @param data данные, которые будут храниться в узле.
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить в список.
     */
    public void addElement(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
        } else {
            Node<T> p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
        }
    }

    /**
     * Получает элемент по заданному индексу.
     *
     * @param index индекс элемента, который нужно получить.
     * @return элемент на указанном индексе.
     * @throws IndexOutOfBoundsException если индекс отрицательный или выходит за пределы списка.
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
        T result = null;
        if (index < 0) throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");

        Node<T> current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                result = current.data;
            }
            count++;
            current = current.next;
        }
        if (result == null) throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        return result;
    }

    /**
     * Удаляет элемент по заданному индексу.
     *
     * @param index индекс элемента, который нужно удалить.
     * @throws IndexOutOfBoundsException если индекс отрицательный или выходит за пределы списка.
     * @throws IllegalStateException     если список пуст при попытке удаления.
     */
    public void removeElement(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (index < 0) throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");

        if (head == null) throw new IllegalStateException("Список пуст");

        if (index == 0) { // Удаление первого элемента
            head = head.next;
            return;
        }

        Node<T> current = head;
        int count = 0;

        while (current.next != null) {
            if (count == index - 1) { // Найден предыдущий узел
                current.next = current.next.next; // Пропускаем удаляемый узел
                return;
            }
            count++;
            current = current.next;
        }

        throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
    }

    /**
     * Возвращает строковое представление элементов списка.
     *
     * @return строка, содержащая элементы списка, разделенные пробелами.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        Node<T> p = head;
        while (p != null) {
            result.append(p.data).append(" ");
            p = p.next;
        }

        return result.toString().trim();
    }
}