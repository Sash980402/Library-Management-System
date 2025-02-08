package org.example.library_management_system.entity.custom;

import org.example.library_management_system.entity.SuperEntity;

public class BookAuthor implements SuperEntity {
    private int bookId;
    private int authorId;

    public BookAuthor() {
    }

    public BookAuthor(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
