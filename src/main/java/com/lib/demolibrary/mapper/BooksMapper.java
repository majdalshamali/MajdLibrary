package com.lib.demolibrary.mapper;

import com.lib.demolibrary.dto.BooksDto;
import com.lib.demolibrary.dto.createDto.BooksCreateDto;
import com.lib.demolibrary.entity.Books;

public class BooksMapper {

    public static BooksDto mapToBooksDto(Books books, BooksDto booksDto)
    {
        booksDto.setTitle(books.getTitle());
        booksDto.setAuthor(books.getAuthor());
        booksDto.setIsbn(books.getIsbn());
        booksDto.setYear(books.getYear());
        booksDto.setId(books.getId());
        return booksDto;
    }
    public static Books mapToBooks(BooksDto booksDto,Books books)
    {
        books.setAuthor(booksDto.getAuthor());
        books.setIsbn(booksDto.getIsbn());
        books.setTitle(booksDto.getTitle());
        books.setYear(booksDto.getYear());
        return books;
    }

    public static BooksDto mapFromBookCreateDto(BooksCreateDto booksCreateDto,BooksDto booksDto)
    {
        booksDto.setAuthor(booksCreateDto.getAuthor());
        booksDto.setIsbn(booksCreateDto.getIsbn());
        booksDto.setYear(booksCreateDto.getYear());
        booksDto.setTitle(booksCreateDto.getTitle());
        return booksDto;
    }
}
