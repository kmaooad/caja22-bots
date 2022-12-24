package edu.kmaooad.services;

import edu.kmaooad.models.Session;
import edu.kmaooad.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SessionServiceTest {

    private final SessionRepository sessionRepository = mock(SessionRepository.class);
    private final SessionService sessionService = new SessionServiceImpl(sessionRepository);

    @Test
    public void shouldReturnUser() {
        when(sessionRepository.findById(any()))
                .then(invocationOnMock -> invocationOnMock.getArgument(0));

        Optional<Session> session = sessionService.getSessionByUserId(any());
        verify(sessionRepository, times(1))
                .findById(any());
    }

    @Test
    public void shouldCreateSession() throws Exception {
        when(sessionRepository.save(any()))
                .then(invocationOnMock -> invocationOnMock.getArgument(0));
        Session session = sessionService.createSessionById(any());
        verify(sessionRepository, times(1))
                .save(any());
    }


}
