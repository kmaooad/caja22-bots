package edu.kmaooad.handlers.experts;

import edu.kmaooad.services.TelegramService;
import edu.kmaooad.handlers.Handler;
import edu.kmaooad.models.Expert;
import edu.kmaooad.statemachine.StateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CreateExpertEmailHandler extends Handler {
    private final TelegramService telegramService;

    @Autowired
    public CreateExpertEmailHandler(TelegramService telegramService) {
        super(null, CreateExpert.EMAIL);
        this.telegramService = telegramService;
    }

    @Override
    public void handle(Message message, StateMachine stateMachine) {
        try {
            Expert expert = stateMachine
                    .getStateCache(message.getChatId(), CreateExpert.TYPE, Expert.class)
                    .orElseThrow();

            expert.setType(message.getText());
            stateMachine.updateStateCache(message.getChatId(), CreateExpert.EMAIL, expert);
            stateMachine.setState(message.getChatId(), CreateExpert.SAVING);
            telegramService.sendMessage(message.getChatId(), "reply.getEmail");

        } catch (Exception exception) {
            telegramService.sendMessage(message.getChatId(), "reply.error");
        }
    }
}
