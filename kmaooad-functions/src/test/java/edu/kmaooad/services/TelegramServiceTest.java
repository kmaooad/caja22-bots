package edu.kmaooad.services;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import edu.kmaooad.telegram.TelegramApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.mockito.Mockito.*;

public class TelegramServiceTest {

    private final TelegramApi telegramApi = mock(TelegramApi.class);
    private final LocaleMessageService localeMessageService = mock(LocaleMessageService.class);

    private final TelegramService telegramService = new TelegramService(telegramApi, localeMessageService);

//    @Test
//    public void shouldSendMessage() throws TelegramApiException {
//        telegramService.sendMessage(1L,  any());
//        verify(telegramApi, times(1))
//                .execute(any(SendMessage.class));
//    }
}
