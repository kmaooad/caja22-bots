package edu.kmaooad.webhook;

import edu.kmaooad.Dispatcher;
import edu.kmaooad.handlers.Handler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.function.Function;

@Component
public class TelegramWebHook implements Function<Update, BotApiMethod<?>> {
    private final Dispatcher dispatcher;

    public TelegramWebHook(Dispatcher dispatcher, List<Handler> handlers) {
        this.dispatcher = dispatcher;
        handlers.forEach(dispatcher::registerHandler);
    }

    @Override
    public BotApiMethod<?> apply(Update update) {
        if (update == null) {
            return new SendMessage(update.getMessage().getChatId().toString(), "reply.error");
        }

        if (update.hasMessage()) {
            Message message = update.getMessage();
            dispatcher.dispatch(message);
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            dispatcher.dispatch(callbackQuery);
        }

        return new SendMessage(update.getMessage().getChatId().toString(), "reply.error");
    }
}