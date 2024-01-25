package com.lib.demolibrary.repository;


import com.lib.demolibrary.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {

    List<BorrowingRecord> findByBookIdAndPatronId(Long bookId, Long patronId);
}
