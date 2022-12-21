package edu.kmaooad.statemachine;

import java.util.Optional;

public interface StateMachine {

    void updateStateCache(Long userId, State.Group group, Object obj) throws Exception;

    <Cache> Optional<Cache> getStateCache(Long userId, State.Group group, Class<Cache> cacheClass);

    void setState(Long userId, State state) throws Exception;
}
