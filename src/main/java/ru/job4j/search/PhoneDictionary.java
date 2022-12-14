package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> contName = (p) -> p.getName().contains(key);
        Predicate<Person> contSurname = (p) -> p.getSurname().contains(key);
        Predicate<Person> contAddress = (p) -> p.getAddress().contains(key);
        Predicate<Person> contPhone = (p) -> p.getPhone().contains(key);
        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (contName.or(contSurname)
                    .or(contAddress).or(contPhone).test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}