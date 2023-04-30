package org.owoodev.book.jbhyunikim;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String name;
    private final List<PhoneNumber> numbers;

    public Person(String name) {
        this.name = name;
        this.numbers = new ArrayList<>();
    }

    public Person(String name, List<PhoneNumber> numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        this.numbers.add(phoneNumber);
    }

    public void addPhoneNumber(String number) {
        this.numbers.add(new PhoneNumber(number));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                '}';
    }

    public static void main(String[] args) {
        Person person = new Person("홍길동");
        person.addPhoneNumber(new PhoneNumber("010-1234-5678"));
        person.addPhoneNumber(new PhoneNumber("01012345678"));
        person.addPhoneNumber("010 1234 5678");
        System.out.println(person);
    }
}
