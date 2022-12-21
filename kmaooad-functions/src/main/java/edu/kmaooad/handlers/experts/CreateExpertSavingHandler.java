package edu.kmaooad.handlers.experts;

import edu.kmaooad.services.ExpertsService;
import edu.kmaooad.services.TelegramService;
import edu.kmaooad.handlers.Handler;
import edu.kmaooad.models.Expert;
import edu.kmaooad.statemachine.State;
import edu.kmaooad.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CreateExpertSavingHandler extends Handler {

    private final TelegramService telegramService;
    private final ExpertsService expertService;

    public CreateExpertSavingHandler(TelegramService telegramService, ExpertsService expertService) {
        super(null, CreateExpert.SAVING);
        this.telegramService = telegramService;
        this.expertService = expertService;
    }

    @Override
    public void handle(Message message, StateMachine stateMachine) {
        try {
            Expert expert = stateMachine
                    .getStateCache(message.getChatId(), CreateExpert.EMAIL, Expert.class)
                    .orElseThrow();

            expert.setEmail(message.getText());
            expertService.createExpert(expert);
            stateMachine.setState(message.getChatId(), new State.Any());
            telegramService.sendMessage(message.getChatId(), "reply.expertWasCreated");
        } catch (Exception exception) {
            telegramService.sendMessage(message.getChatId(), "reply.error");
        }
    }
}
