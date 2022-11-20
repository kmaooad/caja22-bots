package edu.kmaooad;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Bot settings
 */

@Component
@Getter
public class TelegramConfig {

    @Value("${webhook-path}")
    String webhookPath;

    @Value("${bot-name}")
    String botName;

    @Value("${bot-token}")
    String botToken;
}
