package ru.fafurin.quotebook.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BashParser {

    private static final String URL = "http://ibash.org.ru";
    private static final String URL_WITH_PAGES = "http://ibash.org.ru/?page=";
    private static final int START_PAGE = 2;
    private static final int PAGES_COUNT = 60;
    private final Map<Long, String> quotes = new HashMap<>();

    public Map<Long, String> getAllQuotes() {
        parseQuotes(URL);
        for (int page = START_PAGE; page <= PAGES_COUNT; page++) {
            parseQuotes(URL_WITH_PAGES + page);
        }
        return quotes;
    }

    private void parseQuotes(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements sourceQuotes = doc.select(".quote");

        for (Element quoteElement : sourceQuotes) {
            Long id = Long.valueOf(quoteElement
                    .select("b")
                    .first()
                    .text()
                    .substring(1));

            String text = quoteElement
                    .select(".quotbody")
                    .first()
                    .text();

            quotes.put(id, text);
            System.out.printf("Quote with id: %d successfully downloaded...\n", id);
        }
    }
}
