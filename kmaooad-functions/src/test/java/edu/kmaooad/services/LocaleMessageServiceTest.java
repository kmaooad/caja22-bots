package edu.kmaooad.services;

import org.junit.Test;
import org.springframework.context.MessageSource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LocaleMessageServiceTest {

    private final MessageSource messageSource = mock(MessageSource.class);
    private final LocaleMessageService localeMessageService = new LocaleMessageService("${localeTag}", messageSource);

    @Test
    public void shouldGetMessage() {
        when(messageSource.getMessage(any(), any(), any()))
                .then(invocationOnMock -> invocationOnMock.getArgument(0));
        String message = "mess";
        String result = localeMessageService.getMessage(message);
        verify(messageSource, times(1))
                .getMessage(any(), any(), any());
    }

}
