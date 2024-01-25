package com.lib.demolibrary.repository;

import com.lib.demolibrary.entity.Patrons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronsRepository extends JpaRepository<Patrons,Long> {
    Optional<Patrons> findByPatronID(String patronID);
}
