package ru.job4j.pojo;

public class Book {
    private String bookName;
    private int pageCount;

    public Book(String bookName, int pageCount) {
        this.bookName = bookName;
        this.pageCount = pageCount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
