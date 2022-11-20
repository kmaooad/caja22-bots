package edu.kmaooad;


import edu.kmaooad.handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
public class TelegramBot extends SpringWebhookBot {

    private String botPath;
    private String botUsername;
    private String botToken;

    MessageHandler messageHandler;

    @Autowired
    public TelegramBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        super(setWebhook);

        this.messageHandler = messageHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(), "reply.error");
        }
    }

    private BotApiMethod<?> handleUpdate(Update update){
        Message message = update.getMessage();
        if (message != null)
        {
            return messageHandler.answerMessage(message);
        }
        return null;
    }
}
