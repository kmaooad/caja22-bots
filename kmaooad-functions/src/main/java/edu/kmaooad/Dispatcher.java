package edu.kmaooad;

import edu.kmaooad.handlers.Handler;
import edu.kmaooad.services.SessionService;
import edu.kmaooad.models.Session;
import edu.kmaooad.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Dispatcher {

    private final List<Handler> handlers = new ArrayList<>();
    private final SessionService sessionService;
    private final StateMachine stateMachine;

    public Dispatcher(StateMachine stateMachine, SessionService sessionService) {
        this.stateMachine = stateMachine;
        this.sessionService = sessionService;
    }


    public void registerHandler(Handler handler) {
        handlers.add(handler);
    }

    public boolean dispatch(Message message) {

        Optional<Handler> commandHandler = handlers.stream().filter(h -> h.hasCommand(message.getText())).findFirst();
        if (commandHandler.isPresent()) {
            commandHandler.get().handle(message, stateMachine);
            return true;
        }

        Optional<Handler> stateHandler = getHandlerByState(message.getChatId());
        if (stateHandler.isPresent()) {
            stateHandler.get().handle(message, stateMachine);
            return true;
        }

        return false;
    }

    public boolean dispatch(CallbackQuery callbackQuery) {

        Optional<Handler> stateHandler = getHandlerByState(callbackQuery.getMessage().getChatId());
        if (stateHandler.isPresent()) {
            stateHandler.get().handle(callbackQuery, stateMachine);
            return true;
        }

        return false;
    }

    private Optional<Handler> getHandlerByState(Long chatId) {
        Session session = sessionService.getSessionByUserId(chatId).orElseGet(() -> {
                    try {
                        return sessionService.createSessionById(chatId);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return handlers.stream().filter(h -> h.getState() != null).filter(h -> h.getState().key().equalsIgnoreCase(session.currentState)).findFirst();
    }
}
