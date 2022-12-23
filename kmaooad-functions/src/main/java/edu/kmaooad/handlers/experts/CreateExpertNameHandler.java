package edu.kmaooad.handlers.experts;

import edu.kmaooad.services.TelegramService;
import edu.kmaooad.handlers.Handler;
import edu.kmaooad.statemachine.State;
import edu.kmaooad.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
public class CreateExpertNameHandler extends Handler {

    private final TelegramService telegramService;

    public CreateExpertNameHandler(TelegramService telegramService) {
        super(List.of("createExpert"), new State.Any());
        this.telegramService = telegramService;
    }

    @Override
    public void handle(Message message, StateMachine stateMachine) {
        try {
            stateMachine.setState(message.getChatId(), CreateExpert.TYPE);
            telegramService.sendMessage(message.getChatId(), "reply.getName");
        } catch (Exception e) {
            telegramService.sendMessage(message.getChatId(), "reply.error");
        }
    }
}
