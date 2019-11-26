package com.app.service.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookDto {

    private int id;
    @NotNull
    @NotEmpty
    private String bookName;
    @NotNull
    private int authorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
