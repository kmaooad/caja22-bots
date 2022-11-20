package edu.kmaooad.webhook;

import edu.kmaooad.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Function;

@Component
public class TelegramWebHook implements Function<Update, BotApiMethod<?>> {

    private final TelegramBot bot;
    @Autowired
    public TelegramWebHook(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public BotApiMethod<?> apply(Update botUpdate) {
        return bot.onWebhookUpdateReceived(botUpdate);
    }
}