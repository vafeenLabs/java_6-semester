package org.example.lab1;

class LinkedList<T> {
    Node<T> head;

    // Узел связанного списка
    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Добавление элемента в конец списка
    void addElement(T element) {
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

    // Получение элемента по индексу
    T getElement(int index) {
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

    // Удаление элемента по индексу
    void removeElement(int index) {
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