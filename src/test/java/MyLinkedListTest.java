import org.example.lab1.MyLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    void testAddElementToEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addElement(100);
        assertEquals("100", list.toString());
    }

    @Test
    void testAddMultipleElements() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addElement(1);
        list.addElement(2);
        list.addElement(3);
        list.addElement(4);
        assertEquals("1 2 3 4", list.toString());
    }

    @Test
    void testRemoveMiddleElement() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addElement(10);
        list.addElement(20);
        list.addElement(30);
        list.addElement(40);

        list.removeElement(1); // Удаляем элемент с индексом 1 (20)
        assertEquals("10 30 40", list.toString());
    }

    @Test
    void testRemoveLastElement() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addElement(5);
        list.addElement(15);

        list.removeElement(1); // Удаляем последний элемент (15)
        assertEquals("5", list.toString());
    }

    @Test
    void testRemoveFirstAndLastElements() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addElement(50);
        list.addElement(60);

        // Удаляем первый элемент
        list.removeElement(0);
        assertEquals("60", list.toString());

        // Удаляем последний элемент
        list.removeElement(0);
        assertEquals("", list.toString());
    }

    @Test
    void testAddDuplicateElements() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addElement(10);
        list.addElement(10); // Добавляем дубликат
        assertEquals("10 10", list.toString());

        // Проверяем получение элементов
        assertEquals(10, list.getElement(0));
        assertEquals(10, list.getElement(1));
    }

    @Test
    void testGetFirstAndLastElements() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addElement("First");
        list.addElement("Second");

        assertEquals("First", list.getElement(0));
        assertEquals("Second", list.getElement(1));
    }

    @Test
    void testGetFromSingleItemList() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addElement("OnlyOne");

        assertEquals("OnlyOne", list.getElement(0));

        // Проверка выброса исключения при выходе за пределы
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getElement(1);
        });
    }

    @Test
    void testRemoveFromSingleItemList() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addElement("Single");

        // Удаляем единственный элемент
        list.removeElement(0);

        // Проверяем, что список пустой
        assertEquals("", list.toString());

        // Проверка выброса исключения при удалении из пустого списка
        assertThrows(IllegalStateException.class, () -> {
            list.removeElement(0);
        });
    }
}
