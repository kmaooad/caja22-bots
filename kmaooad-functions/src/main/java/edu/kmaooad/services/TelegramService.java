package edu.kmaooad.services;

import edu.kmaooad.telegram.TelegramApi;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class TelegramService {
    private final TelegramApi telegramApi;
    private final LocaleMessageService localeMessageService;

    public TelegramService(TelegramApi telegramApi, LocaleMessageService localeMessageService) {
        this.telegramApi = telegramApi;
        this.localeMessageService = localeMessageService;
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = SendMessage
                .builder()
                .text(localeMessageService.getMessage(text))
                .chatId(chatId.toString())
                .parseMode(ParseMode.HTML)
                .build();
        try {
            telegramApi.execute(sendMessage);
        } catch (Exception ignored) {

        }
    }
}
