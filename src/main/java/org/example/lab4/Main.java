package org.example.lab4;

import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        List<Person> persons = new PersonDataReader().loadPeopleFromCsv("foreign_names.csv");
    }
}