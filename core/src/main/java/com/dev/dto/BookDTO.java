package com.dev.dto;


import org.hibernate.validator.constraints.NotBlank;

public class BookDTO extends BaseDTO{

    @NotBlank
    private String name;
    @NotBlank
    private String isbn;
    @NotBlank
    private String author;
    private String userName;

    public Long getId() {
        return id;
    }

    public BookDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public BookDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
