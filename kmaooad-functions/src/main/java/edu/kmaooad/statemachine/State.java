package edu.kmaooad.statemachine;

public interface State {

    default String key() {
        return getClass().getName();
    }

    interface Group {
        default String group() {
            return getClass().getName();
        }
    }

    class Any implements State {}
}
