package ru.job4j.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class College {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Student student = new Student();
        student.setFullName("Ivanov Ivan Tihonovich");
        student.setGroupName("A1");
        student.setReceiptDate(LocalDate.of(2019, 4, 15));
        System.out.println("ФИО студента : " + student.getFullName());
        System.out.println("Номер группы : " + student.getGroupName());
        System.out.println("Дата поступления : " + student.getReceiptDate().format(formatter));
    }
}
