package edu.kmaooad.handlers.experts;

import edu.kmaooad.services.TelegramService;
import edu.kmaooad.handlers.Handler;
import edu.kmaooad.models.Expert;
import edu.kmaooad.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CreateExpertTypeHandler extends Handler {

    private final TelegramService telegramService;

    public CreateExpertTypeHandler(TelegramService telegramService) {
        super(null, CreateExpert.TYPE);
        this.telegramService = telegramService;
    }

    @Override
    public void handle(Message message, StateMachine stateMachine) {
        try {
            //asked for name in previous step
            String expertName = message.getText();
            Expert expert = new Expert(null, null, expertName);
            stateMachine.updateStateCache(message.getChatId(), CreateExpert.NAME, expert);
            stateMachine.setState(message.getChatId(), CreateExpert.EMAIL);
            telegramService.sendMessage(message.getChatId(), "reply.getType");
        } catch (Exception exception) {
            telegramService.sendMessage(message.getChatId(), "reply.error");
        }
    }
}