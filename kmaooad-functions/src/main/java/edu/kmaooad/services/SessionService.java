package edu.kmaooad.services;

import edu.kmaooad.models.Session;
import edu.kmaooad.statemachine.State;

import java.util.Optional;

public interface SessionService {

    Optional<Session> getSessionByUserId(Long userId);

    Session createSessionById(Long userId) throws Exception;

    Session updateStateById(Long userId, State state) throws Exception;

    Session updateCacheById(Long userId, State.Group group, String cache) throws Exception;

    String getCacheById(Long userId, State.Group group) throws Exception;
}
