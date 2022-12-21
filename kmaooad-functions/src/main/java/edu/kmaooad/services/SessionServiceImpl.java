package edu.kmaooad.services;

import edu.kmaooad.statemachine.State;
import edu.kmaooad.models.Session;
import edu.kmaooad.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> getSessionByUserId(Long userId) {
        return sessionRepository.findById(userId);
    }

    @Override
    public Session createSessionById(Long userId) throws Exception {
        Optional<Session> session = sessionRepository.findById(userId);

        if (session.isPresent()) {
            throw new Exception();
        }

        return sessionRepository.save(new Session(userId));
    }

    @Override
    public Session updateStateById(Long userId, State state) throws Exception {
        Optional<Session> session = sessionRepository.findById(userId);

        if (session.isEmpty()) {
            session = Optional.of(createSessionById(userId));
        }

        session.get().currentState = state.key();
        return sessionRepository.save(session.get());
    }

    @Override
    public Session updateCacheById(Long userId, State.Group group, String cache) throws Exception {
        Optional<Session> session = sessionRepository.findById(userId);

        if (session.isEmpty()) {
            session = Optional.of(createSessionById(userId));
        }

        session.get().statesCache.put(group.group(), cache);
        return sessionRepository.save(session.get());
    }

    @Override
    public String getCacheById(Long userId, State.Group group) throws Exception {
        Optional<Session> session = sessionRepository.findById(userId);

        if (session.isEmpty()) {
            throw new Exception();
        }

        Map<String, String> cacheMap = session.get().statesCache;
        return cacheMap.get(group.group());
    }
}
