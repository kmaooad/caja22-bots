package edu.kmaooad.statemachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.services.SessionService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StateMachineImpl implements StateMachine {
    private final SessionService sessionService;

    public StateMachineImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void setState(Long userId, State state) throws Exception {
        sessionService.updateStateById(userId, state);
    }

    @Override
    public void updateStateCache(Long userId, State.Group group, Object obj) throws Exception {
        String cachedString = new ObjectMapper().writeValueAsString(obj);
        sessionService.updateCacheById(userId, group, cachedString);
    }
    @Override
    public <Cache> Optional<Cache> getStateCache(Long userId, State.Group group, Class<Cache> cacheClass) {
        try {
            String cache = sessionService.getCacheById(userId, group);
            ObjectMapper mapper = new ObjectMapper();

            return Optional.of(mapper.readValue(cache, cacheClass));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
