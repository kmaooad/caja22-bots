package edu.kmaooad.handlers;

import edu.kmaooad.statemachine.State;
import edu.kmaooad.statemachine.StateMachine;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class Handler {

    protected Handler(List<String> commands, State state){
        this.commands = commands;
        this.state = state;
    }

    public abstract void handle(Message message, StateMachine stateMachine);

    public void handle(CallbackQuery callbackQuery, StateMachine stateMachine) {}

    final public List<String> getCommands() {
        return commands;
    }

    final public State getState() {
        return state;
    }

    final public boolean hasCommand(String command) {
        return getCommands() != null && getCommands().stream().anyMatch(c -> ("/" + c).equalsIgnoreCase(command));
    }

    private List<String> commands;
    private State state;
}
