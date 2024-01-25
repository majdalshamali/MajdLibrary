package com.lib.demolibrary.service;

import com.lib.demolibrary.dto.BooksDto;

import java.util.List;

public interface IBooksService {

    void createBook(BooksDto booksDto);
    List<BooksDto> listofBooks();
    BooksDto findById(Long id);

    boolean updateBook(Long id,BooksDto booksDto);

    boolean deleteBook(Long id);
}
