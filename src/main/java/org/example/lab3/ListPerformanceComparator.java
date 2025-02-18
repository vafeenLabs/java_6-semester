package org.example.lab3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс ListPerformanceComparator используется для сравнения производительности
 * операций над списками, реализованными с помощью ArrayList и LinkedList.
 *
 * Он измеряет время выполнения различных операций, таких как добавление,
 * удаление и получение элементов, при разном количестве вызовов.
 */
public class ListPerformanceComparator {
    /**
     * Начальное количество выполнений операций.
     */
    private static final int INITIAL_EXECUTIONS_COUNT = 1000;

    /**
     * Максимальное количество выполнений операций.
     */
    private static final int MAX_EXECUTIONS_COUNT = 8000;

    /**
     * Множитель для увеличения количества выполнений операций.
     */
    private static final int EXECUTIONS_COUNT_MULTIPLIER = 2;

    /**
     * Размер списков, используемых для тестирования.
     */
    private static final int LIST_SIZE = 100000;

    /**
     * Основной метод для сравнения производительности ArrayList и LinkedList.
     *
     * Создает списки заданного размера и выполняет тесты с увеличивающимся
     * количеством вызовов операций (от начального до максимального значения).
     */
    static void compare() {
        var arrayList = new ArrayList<Integer>(LIST_SIZE);
        var linkedList = new LinkedList<Integer>();
        for (int i = 0; i < LIST_SIZE; ++i) {
            arrayList.add(0);
            linkedList.add(0);
        }
        int invokesCount = INITIAL_EXECUTIONS_COUNT;
        do {
            compareListPerformance(arrayList, linkedList, invokesCount);
            System.out.println();
            invokesCount *= EXECUTIONS_COUNT_MULTIPLIER;
        } while (invokesCount <= MAX_EXECUTIONS_COUNT);
    }

    /**
     * Сравнивает производительность различных операций над ArrayList и LinkedList.
     *
     * @param arrayList список на основе ArrayList для тестирования.
     * @param linkedList список на основе LinkedList для тестирования.
     * @param executionsCount количество раз, которое будет выполнена каждая операция.
     */
    private static void compareListPerformance(ArrayList<Integer> arrayList, LinkedList<Integer> linkedList, int executionsCount) {
        var arrayListAddToHeadTime = measureExecutionTime(executionsCount, () -> arrayList.add(0));
        var linkedListAddToHeadTime = measureExecutionTime(executionsCount, () -> linkedList.add(0));

        var arrayListRemoveFromHeadTime = measureExecutionTime(executionsCount, arrayList::removeFirst);
        var linkedListRemoveFromHeadTime = measureExecutionTime(executionsCount, linkedList::removeFirst);

        var arrayListAddToMiddleTime = measureExecutionTime(executionsCount, () -> arrayList.add(arrayList.size() / 2, 0));
        var linkedListAddToMiddleTime = measureExecutionTime(executionsCount, () -> linkedList.add(linkedList.size() / 2, 0));

        var arrayListRemoveFromMiddleTime = measureExecutionTime(executionsCount, () -> arrayList.remove(arrayList.size() / 2));
        var linkedListRemoveFromMiddleTime = measureExecutionTime(executionsCount, () -> linkedList.remove(linkedList.size() / 2));

        var arrayListAddToEndTime = measureExecutionTime(executionsCount, () -> arrayList.add(arrayList.size(), 0));
        var linkedListAddToEndTime = measureExecutionTime(executionsCount, () -> linkedList.add(linkedList.size(), 0));

        var arrayListRemoveFromEndTime = measureExecutionTime(executionsCount, arrayList::removeLast);
        var linkedListRemoveFromEndTime = measureExecutionTime(executionsCount, linkedList::removeLast);

        var arrayListGetFromHeadTime = measureExecutionTime(executionsCount, () -> arrayList.get(0));
        var linkedListGetFromHeadTime = measureExecutionTime(executionsCount, () -> linkedList.get(0));

        var arrayListGetFromMiddleTime = measureExecutionTime(executionsCount, () -> arrayList.get(arrayList.size() / 2));
        var linkedListGetFromMiddleTime = measureExecutionTime(executionsCount, () -> linkedList.get(linkedList.size() / 2));

        var arrayListGetFromEndTime = measureExecutionTime(executionsCount, arrayList::getLast);
        var linkedListGetFromEndTime = measureExecutionTime(executionsCount, linkedList::getLast);

        System.out.printf("Количество выполнений: %s\n", executionsCount);
        System.out.print("Операция            | Array List | Linked List\n");
        System.out.printf("Добавить в начало     | %10s | %11s\n", arrayListAddToHeadTime, linkedListAddToHeadTime);
        System.out.printf("Добавить в середину   | %10s | %11s\n", arrayListAddToMiddleTime, linkedListAddToMiddleTime);
        System.out.printf("Добавить в конец      | %10s | %11s\n", arrayListAddToEndTime, linkedListAddToEndTime);
        System.out.format("Удалить из начала    | %10s | %11s\n", arrayListRemoveFromHeadTime, linkedListRemoveFromHeadTime);
        System.out.printf("Удалить из середины  | %10s | %11s\n", arrayListRemoveFromMiddleTime, linkedListRemoveFromMiddleTime);
        System.out.printf("Удалить из конца     | %10s | %11s\n", arrayListRemoveFromEndTime, linkedListRemoveFromEndTime);
        System.out.printf("Получить из начала    | %10s | %11s\n", arrayListGetFromHeadTime, linkedListGetFromHeadTime);
        System.out.printf("Получить из середины  | %10s | %11s\n", arrayListGetFromMiddleTime, linkedListGetFromMiddleTime);
        System.out.printf("Получить из конца     | %10s | %11s\n", arrayListGetFromEndTime, linkedListGetFromEndTime);

    }

    /**
     * Измеряет время выполнения указанного количества операций.
     *
     * @param executionsCount количество раз выполнения задачи.
     * @param task задача (операция), которую нужно выполнить.
     * @return время выполнения операции в наносекундах.
     */
    private static long measureExecutionTime(int executionsCount, Runnable task) {
        return calculateTime(() -> {
            for (int i = 0; i < executionsCount; ++i) {
                task.run();
            }
        });
    }

    /**
     * Измеряет время выполнения одной задачи (операции).
     *
     * @param task задача (операция), которую нужно выполнить.
     * @return время выполнения операции в наносекундах.
     */
    private static long calculateTime(Runnable task) {
        long startNano = System.nanoTime();
        task.run();
        long endNano = System.nanoTime();
        return endNano - startNano;
    }
}

