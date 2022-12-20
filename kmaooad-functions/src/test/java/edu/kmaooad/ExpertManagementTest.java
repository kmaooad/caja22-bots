package edu.kmaooad;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Update;


public class ExpertManagementTest {

    final static String botUpdateQuery = "{\"update_id\":983721550,\n" +
            "\"message\":{\"message_id\":6,\"from\":{\"id\":421325006,\"is_bot\":false,\"" +
            "first_name\":\"John\",\"last_name\":\"Smith\",\"username\":\"Sun_0k" +
            "\",\"language_code\":\"ru\"},\"chat\":{\"id\":421325006,\"first_name\":\"John\",\"l" +
            "ast_name\":\"Smith\",\"username\":\"Sun_0k\",\"type\":\"private\"},\"date\":1664723185,\"" +
            "text\":\"addExpert\"}}";

    @Test
    public void createBotUpdate(){

        Update update = new Update();
    }

}
