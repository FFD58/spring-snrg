package ru.fafurin.quotebook.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fafurin.quotebook.model.Chat;
import ru.fafurin.quotebook.model.Quote;
import ru.fafurin.quotebook.repository.ChatRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class BotService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private QuoteService quoteService;

    private final TelegramBot bot = new TelegramBot("6269429359:AAGP3tXrUD2mIUe75hMiVy-i1iC0AiEE6Fc");

    private static final Long FIRST_QUOTE_ID = 1L;
    private static final Long LAST_QUOTE_ID = 2992L;

    public BotService() {
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                updateHandle(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                e.printStackTrace();
            }
        });
    }

    private void updateHandle(Update update) {
        String text = update.message().text();
        System.out.println(text);
        Long chatId = update.message().chat().id();
        Optional<Chat> chatOptional = chatRepository.findByChatId(chatId);
        Chat chat = new Chat();
        if (chatOptional.isPresent()) {
            chat = chatOptional.get();
        } else {
            chat.setChatId(chatId);
            chat.setLastQuoteId(0L);
            chatRepository.save(chat);
        }

        switch (text) {
            case "/start", "/next" -> {
                sendNextQuote(chat);
            }
            case "/prev" -> {
                sendPrevQuote(chat);
            }
            case "/random" -> {
                sendRandomQuote(chat);
            }
        }
    }

    private void sendRandomQuote(Chat chat) {
        Long quoteId = new Random().nextLong(1, LAST_QUOTE_ID + 1);
        Quote quote = quoteService.getById(quoteId);
        sendText(chat.getChatId(), quote.getText());
    }

    private void sendPrevQuote(Chat chat) {
        Long quoteId = chat.getLastQuoteId();
        quoteId--;
        if (quoteId < FIRST_QUOTE_ID) quoteId = FIRST_QUOTE_ID;
        chat.setLastQuoteId(quoteId);
        Quote quote = quoteService.getById(quoteId);
        sendText(chat.getChatId(), quote.getText());
        chatRepository.save(chat);
    }

    private void sendNextQuote(Chat chat) {
        Long quoteId = chat.getLastQuoteId();
        quoteId++;
        if (quoteId > LAST_QUOTE_ID) quoteId = LAST_QUOTE_ID;
        chat.setLastQuoteId(quoteId);
        Quote quote = quoteService.getById(quoteId);
        sendText(chat.getChatId(), quote.getText());
        chatRepository.save(chat);
    }

    private void sendText(Long chatId, String text) {
        SendResponse response = bot.execute(new SendMessage(chatId, text));
        System.out.println(response);
    }
}
