package ru.fafurin.quotebook.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fafurin.quotebook.model.Quote;
import ru.fafurin.quotebook.repository.QuoteRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class QuoteService {

    private QuoteRepository repository;
    private BashParser bashParser;

    public List<Quote> getAll() {
        return repository.findAll();
    }

    public Quote getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Quote save(Quote quote) {
        return repository.save(quote);
    }

    public int fillDB() {
        int downloadedQuotes = 0;
        Map<Long, String> quotes = bashParser.getAllQuotes();
        for (Map.Entry<Long, String> entry : quotes.entrySet()) {
            if (repository.findById(entry.getKey()).isEmpty()) {
                Quote quote = new Quote();
                quote.setOriginalId(entry.getKey());
                quote.setText(entry.getValue());
                repository.save(quote);
                downloadedQuotes++;
            }
        }
        return downloadedQuotes;
    }

}
