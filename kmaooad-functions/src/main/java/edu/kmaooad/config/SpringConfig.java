package edu.kmaooad.config;

import edu.kmaooad.TelegramBot;
import edu.kmaooad.TelegramConfig;
import edu.kmaooad.handlers.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class SpringConfig {
    private final TelegramConfig telegramConfig;

    @Autowired
    public SpringConfig(TelegramConfig telegramConfig) {
        this.telegramConfig = telegramConfig;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public TelegramBot springWebhookBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        TelegramBot bot = new TelegramBot(setWebhook, messageHandler);

        bot.setBotPath(telegramConfig.getWebhookPath());
        bot.setBotUsername(telegramConfig.getBotName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;
    }
}
