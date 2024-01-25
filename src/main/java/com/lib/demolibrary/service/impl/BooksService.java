package com.lib.demolibrary.service.impl;

import com.lib.demolibrary.dto.BooksDto;
import com.lib.demolibrary.entity.Books;
import com.lib.demolibrary.exception.ResourceAlreadyExistException;
import com.lib.demolibrary.exception.ResourceNotFoundException;
import com.lib.demolibrary.mapper.BooksMapper;
import com.lib.demolibrary.repository.BooksRepository;
import com.lib.demolibrary.service.IBooksService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.lib.demolibrary.mapper.BooksMapper.mapToBooks;
import static com.lib.demolibrary.mapper.BooksMapper.mapToBooksDto;

@Service
@AllArgsConstructor
public class BooksService implements IBooksService {

    private BooksRepository booksRepository;

    @Override
    public void createBook(BooksDto booksDto) {

        Books book = BooksMapper.mapToBooks(booksDto, new Books());
        Optional<Books> optionalBooks = booksRepository.findByIsbn(book.getIsbn());
        if (optionalBooks.isPresent()) {
            throw new ResourceAlreadyExistException("Book already inserted with the given ISBN = " + book.getIsbn());
        }
        booksRepository.save(book);
    }

    @Override
    public List<BooksDto> listofBooks() {
        List<Books> listOfBooks = booksRepository.findAll();
        List<BooksDto> booksDtos = new ArrayList<>();
        if (!listOfBooks.isEmpty()) {
            for (Books book : listOfBooks) {
                BooksDto booksDto = mapToBooksDto(book, new BooksDto());
                booksDtos.add(booksDto);
            }
        }
        return booksDtos;
    }

    @Override
    @Cacheable("books")
    public BooksDto findById(Long id) {
        Books book = booksRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "ID", id.toString())
        );
        return mapToBooksDto(book, new BooksDto());
    }

    @Override
    public boolean updateBook(Long id, BooksDto booksDto) {
        Optional<Books> books = booksRepository.findById(id);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("Book", "id", id.toString());
        }
        Books updatedBook = mapToBooks(booksDto, books.get());
        booksRepository.save(updatedBook);
        return true;
    }

    @Override
    public boolean deleteBook(Long id) {
        boolean isDeleted = false;
        Optional<Books> books = booksRepository.findById(id);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("Book", "id", id.toString());
        }
        booksRepository.deleteById(id);
        isDeleted = true;
        return isDeleted;
    }


}
