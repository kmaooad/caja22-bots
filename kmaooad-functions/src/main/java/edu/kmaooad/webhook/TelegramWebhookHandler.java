package edu.kmaooad.webhook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class TelegramWebhookHandler extends FunctionInvoker<Update, BotApiMethod<?>> {

    /**
     * This function listens at endpoint "/api/TelegramWebhook". To invoke it using "curl" command in bash:
     * curl -d "HTTP Body" http://localhost:8080/api/TelegramWebhook
     */

    @FunctionName("TelegramWebhook")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        Update update;
        try {
            final String body = request.getBody().get();

            final ObjectMapper mapper = new ObjectMapper();
            update = mapper.readValue(body, Update.class);
            handleRequest(update, context);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        return request
                .createResponseBuilder(HttpStatus.OK)
                .build();
    }
}