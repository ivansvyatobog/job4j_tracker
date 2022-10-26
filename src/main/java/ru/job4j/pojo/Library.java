package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Щелкунчик", 150);
        Book book2 = new Book("Красная Шапочка", 89);
        Book book3 = new Book("Война и Мир", 1300);
        Book book4 = new Book("Clean Code", 500);
        Book[] books = new Book[4];
        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        books[3] = book4;
        for (int i = 0; i < books.length; i++) {
            System.out.println("Название книги : " + books[i].getBookName() + ", количество страниц : " + books[i].getPageCount());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int i = 0; i < books.length; i++) {
            System.out.println("Название книги : " + books[i].getBookName() + ", количество страниц : " + books[i].getPageCount());
        }
        for (int i = 0; i < books.length; i++) {
            if ("Clean Code".equals(books[i].getBookName())) {
                System.out.println("Название книги : " + books[i].getBookName() + ", количество страниц : " + books[i].getPageCount());
            }
        }
    }
}
