package org.example.lab4;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с данными о людях, считываемыми из CSV файла.
 */
class PersonDataReader {

    /**
     * Метод для выгрузки данных о людях из CSV файла.
     *
     * @param csvFilePath путь к CSV файлу.
     * @return список людей, загруженных из файла.
     * @throws Exception если возникает ошибка при чтении файла.
     */
    public List<Person> loadPeopleFromCsv(String csvFilePath) throws Exception {
        List<Person> people = new ArrayList<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader reader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in, StandardCharsets.UTF_8))
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(';')
                             .build())
                     .build()) {
            if (reader == null) {
                throw new FileNotFoundException("Файл не найден: " + csvFilePath);
            }

            String[] nextLine;
            // Пропускаем заголовок
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                // id;name;gender;BirthDate;Division;Salary
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                String gender = nextLine[2];
                String birthDate = nextLine[3];
                String division = nextLine[4];
                double salary = Double.parseDouble(nextLine[5]);
                Person person = new Person(id, name, gender, division, salary, birthDate);
                people.add(person);
            }
        }

        return people;
    }
}

