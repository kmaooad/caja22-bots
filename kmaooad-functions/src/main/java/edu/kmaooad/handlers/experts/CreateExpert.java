package edu.kmaooad.handlers.experts;

import edu.kmaooad.statemachine.State;

public enum CreateExpert implements State, State.Group {
    NAME, EMAIL, TYPE, SAVING;

    @Override
    public String key() {
        return getClass().getName() + "." + name();
    }
}
