package org.example.library_management_system.entity.custom;

import org.example.library_management_system.entity.SuperEntity;

public class SubCategories implements SuperEntity {
    private int bookId;
    private int categoryId;


    public SubCategories() {
    }

    public SubCategories(int bookId, int categoryId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
