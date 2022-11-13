package edu.kmaooad;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TelegramWebHook implements Function<BotUpdate, BotUpdateResult> {

    @Override
    public BotUpdateResult apply(BotUpdate botUpdate) {
        if (botUpdate.getMessageId() == null) {
            return BotUpdateResult.Success(botUpdate.getMessageId());
        } else {
            return BotUpdateResult.Error("Error occur");
        }
    }
}