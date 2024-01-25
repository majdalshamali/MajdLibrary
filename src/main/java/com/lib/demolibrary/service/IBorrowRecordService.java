package com.lib.demolibrary.service;

public interface IBorrowRecordService {

    boolean createBorrowRecord(Long bookId,Long patronId);
    boolean createReturnRecord(Long bookId,Long patronId);
}
