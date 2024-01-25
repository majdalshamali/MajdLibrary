package com.lib.demolibrary.service.impl;


import com.lib.demolibrary.entity.Books;
import com.lib.demolibrary.entity.BorrowingRecord;
import com.lib.demolibrary.entity.Patrons;
import com.lib.demolibrary.exception.ResourceAlreadyExistException;
import com.lib.demolibrary.exception.ResourceNotFoundException;
import com.lib.demolibrary.repository.BooksRepository;
import com.lib.demolibrary.repository.BorrowingRecordRepository;
import com.lib.demolibrary.repository.PatronsRepository;
import com.lib.demolibrary.service.IBooksService;
import com.lib.demolibrary.service.IBorrowRecordService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowRecordService implements IBorrowRecordService {

    private BooksRepository booksRepository;
    private PatronsRepository patronsRepository;
    private BorrowingRecordRepository borrowingRecordRepository;

    @Override
    @Transactional
    public boolean createBorrowRecord(Long bookId, Long patronId) {
        Optional<Books> book = Optional.ofNullable(booksRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId.toString())
        ));
        Optional<Patrons> patron = Optional.ofNullable(patronsRepository.findById(patronId).orElseThrow(
                () -> new ResourceNotFoundException("Patron", "id", patronId.toString())
        ));
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .stream().filter(BorrowingRecord::isBorrowed)
                .toList();
        if(!borrowingRecords.isEmpty()){
            throw new ResourceAlreadyExistException("Book has been already borrowed to the Patron");
        }
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBorrowed(true);
        borrowingRecord.setBook(book.get());
        borrowingRecord.setPatron(patron.get());
        borrowingRecord.setBorrowDate(LocalDateTime.now());
        borrowingRecordRepository.save(borrowingRecord);
        return false;
    }

    @Override
    @Transactional
    public boolean createReturnRecord(Long bookId, Long patronId) {
        Optional<Books> book = Optional.ofNullable(booksRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId.toString())
        ));
        Optional<Patrons> patron = Optional.ofNullable(patronsRepository.findById(patronId).orElseThrow(
                () -> new ResourceNotFoundException("Patron", "id", patronId.toString())
        ));
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .stream().filter(BorrowingRecord::isBorrowed)
                .toList();
        if(borrowingRecords.isEmpty())
        {
            throw new ResourceNotFoundException("Borrowing record"," Book and Patron Ids ",bookId.toString()+" & "+ patronId.toString());
        }
        BorrowingRecord updatedBorrowingRecords = borrowingRecords.get(0);
        updatedBorrowingRecords.setReturnDate(LocalDateTime.now());
        updatedBorrowingRecords.setBorrowed(false);
        borrowingRecordRepository.save(updatedBorrowingRecords);
        return false;
    }
}
