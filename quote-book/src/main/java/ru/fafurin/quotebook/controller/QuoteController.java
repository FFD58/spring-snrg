package ru.fafurin.quotebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fafurin.quotebook.model.Quote;
import ru.fafurin.quotebook.service.QuoteService;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/fillDB")
    public ResponseEntity<String> fillDB() {
        int savedQuotesCount = quoteService.fillDB();
        return new ResponseEntity<>(String.format("%d quotes added to db!", savedQuotesCount), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Quote>> getAll() {
        List<Quote> quotes = quoteService.getAll();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getById(@PathVariable("id") Long id) {
        Quote quote = quoteService.getById(id);
        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }
}
