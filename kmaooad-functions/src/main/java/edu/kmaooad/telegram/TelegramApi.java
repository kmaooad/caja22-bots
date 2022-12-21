package edu.kmaooad.telegram;

import edu.kmaooad.config.TelegramConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class TelegramApi extends DefaultAbsSender {
    TelegramConfig telegramConfig;

    protected TelegramApi(TelegramConfig telegramConfig) {
        super(new DefaultBotOptions());
        this.telegramConfig = telegramConfig;
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getBotToken();
    }
}
