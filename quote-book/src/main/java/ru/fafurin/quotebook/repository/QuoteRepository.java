package ru.fafurin.quotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fafurin.quotebook.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
